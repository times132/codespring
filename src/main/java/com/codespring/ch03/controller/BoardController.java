package com.codespring.ch03.controller;

import com.codespring.ch03.domain.BoardAttachVO;
import com.codespring.ch03.domain.BoardVO;
import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.domain.PageDTO;
import com.codespring.ch03.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

//    @GetMapping("list")
//    public void list(Model model){
//        log.info("-----list-----");
//
//        model.addAttribute("list", boardService.getList());
//    }

    private void deleteFiles(List<BoardAttachVO> attachList){
        if (attachList == null || attachList.size() <= 0){
            return;
        }

        log.info("delete attach files...........");
        log.info(attachList);

        attachList.forEach(attach -> {
            try {
                Path file = Paths.get("D:\\upload\\" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());

                Files.delete(file);
                if (Files.probeContentType(file).startsWith("image")){
                    Path thumbNail = Paths.get("D:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
                    Files.delete(thumbNail);
                }
            }catch (Exception e){
                log.error("delete file error" + e.getMessage());
            }
        });
    }

    @GetMapping("list")
    public void list(Model model, Criteria cri){
        log.info("================================");
        log.info("-----list-----");

        model.addAttribute("list", boardService.getList(cri));

        int total = boardService.getTotal(cri);
        log.info("total count: " + total);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @GetMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public void register(){

    }

    @PostMapping("/register")
    @PreAuthorize("isAuthenticated()")
    public String register(BoardVO boardVO, RedirectAttributes redirectAttributes){
        log.info("================================");
        log.info("-----register-----");

        if (boardVO.getAttachList() != null){
            boardVO.getAttachList().forEach(attach -> log.info(attach));
        }

        log.info("=================================");

        boardService.register(boardVO);

        redirectAttributes.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Integer bno, @ModelAttribute("cri") Criteria cri, Model model){
        log.info("================================");
        log.info("-----get-----");

        model.addAttribute("board", boardService.get(bno));
    }

    @PreAuthorize("principal.username == #boardVO.writer")
    @PostMapping("/modify")
    public String modify(BoardVO boardVO, @ModelAttribute Criteria cri, RedirectAttributes redirectAttributes){
        log.info("================================");
        log.info("-----modify-----" + boardVO);

        if (boardService.modify(boardVO)){
            redirectAttributes.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list" + cri.getListLink();
    }

    @PreAuthorize("principal.username == #writer")
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Integer bno, @ModelAttribute Criteria cri, RedirectAttributes redirectAttributes, String writer){
        log.info("================================");
        log.info("-----remove-----" + bno);

        List<BoardAttachVO> attachList = boardService.getAttachList(bno);

        if (boardService.remove(bno)){
            deleteFiles(attachList);
            redirectAttributes.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list" + cri.getListLink();
    }

    @GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(Integer bno){
        log.info("getAttachList " + bno);

        return new ResponseEntity<>(boardService.getAttachList(bno), HttpStatus.OK);
    }


}
