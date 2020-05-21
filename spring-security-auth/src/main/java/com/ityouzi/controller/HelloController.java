package com.ityouzi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-21 09:55
 * @Description:
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


}
