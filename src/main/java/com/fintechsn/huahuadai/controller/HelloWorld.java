package com.fintechsn.huahuadai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorld {

    @RequestMapping("world")
    public String world() {
        System.out.println("hello world");
        return "hello world";
    }

}
