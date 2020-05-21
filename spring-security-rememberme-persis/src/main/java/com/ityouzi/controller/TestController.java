package com.ityouzi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-19 20:33
 * @Description:
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/rememberme")
    public String rememberme() {
        return "rememberme";
    }



}
