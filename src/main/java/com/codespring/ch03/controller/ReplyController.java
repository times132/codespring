package com.codespring.ch03.controller;

import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.domain.ReplyPageDTO;
import com.codespring.ch03.domain.ReplyVO;
import com.codespring.ch03.service.ReplyService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@Log4j
public class ReplyController {

    @Setter(onMethod_ = {@Autowired})
    private ReplyService service;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){
        //Post방식으로 동작하고 json데이터를 받아 @RequestBody를 이용하여 ReplyVO 타입으로 변환
        log.info("ReplyVo: " + vo);

        int insertCount = service.register(vo);

        log.info("Reply INSERT COUNT: " + insertCount);

        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") int bno){

        log.info("---getList---");

        Criteria cri = new Criteria(page, 10);

        log.info(cri);

        return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable int rno){

        log.info("---get reply---");

        return new ResponseEntity<>(service.read(rno), HttpStatus.OK);
    }

    @PreAuthorize("principal.username == #vo.replyer")
    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable int rno){

        log.info("---remove reply");

        return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable int rno){

        log.info("---modify reply---");

        vo.setRno(rno);

        log.info("rno: " + rno);

        return service.modify(vo) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
