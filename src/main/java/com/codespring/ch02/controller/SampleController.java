package com.codespring.ch02.controller;

import com.codespring.ch02.domain.SampleDTO;
import com.codespring.ch02.domain.SampleDTOList;
import com.codespring.ch02.domain.TodoDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("")
    public void basic(){
        log.info("-----basic-----");
    }

    @RequestMapping(value = "/ex01", method = RequestMethod.GET)
    public String ex01(SampleDTO dto){
        log.info("" + dto);
        log.info(dto.toString());

        return "ex01";
    }

    @RequestMapping(value = "ex02", method = RequestMethod.GET)
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){
        log.info("name: " + name);
        log.info("age: " + age);

        return "ex02";
    }

    @RequestMapping(value = "ex02List", method = RequestMethod.GET)
    public String ex02List(@RequestParam("ids")ArrayList<String> ids){
        log.info("ids: " + ids);

        return "ex02List";
    }

    @RequestMapping(value = "ex02Bean", method = RequestMethod.GET)
    public String ex02Bean(SampleDTOList list){
        log.info("list dtos: " + list);
        return "ex02Bean";
    }

    @RequestMapping(value = "ex03", method = RequestMethod.GET)
    public String ex03(TodoDTO todo){
        log.info("todo: " + todo);

        return "ex03";
    }

    @RequestMapping(value = "ex04", method = RequestMethod.GET)
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page){
        log.info("dto: " + dto);
        log.info("page: " + page);

        return "sample/ex04";
    }

    @RequestMapping(value = "ex04Redirect", method = RequestMethod.GET)
    public String ex04Redirect(SampleDTO dto, RedirectAttributes attributes){
        attributes.addAttribute("name", "aaa");
        attributes.addFlashAttribute("age", 10);
        log.info("RedirectAttributes: " + attributes.toString());

        return "sample/ex04Re";
    }

    @RequestMapping(value = "ex05", method = RequestMethod.GET)
    public @ResponseBody SampleDTO ex05(){ //@ResponseBody = 자바 객체를 HTTP 응답 몸체로 전송함
        log.info("-----ex05-----");

        SampleDTO dto = new SampleDTO();
        dto.setName("aaa");
        dto.setAge(26);

        return dto;
    }

    @RequestMapping(value = "ex06", method = RequestMethod.GET)
    public ResponseEntity<String> ex06(){
        log.info("-----ex06-----");

        String msg = "{\"name\": \"times\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Context-Type", "application/json; charset=UTF-8");

        return new ResponseEntity<>(msg, headers, HttpStatus.OK);
    }
}
