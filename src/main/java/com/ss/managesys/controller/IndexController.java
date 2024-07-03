package com.ss.managesys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class IndexController {

//    @GetMapping("/test")
//    public String testFind(){
//        return "sucess!!!!!";
//    }

    @RequestMapping("/managesys/web/**")
    public String index() {
//        log.info("111111:-{}", "/ss/**");
//        ModelAndView mv =  new ModelAndView("index");
        return "/index.html";
    }
}
