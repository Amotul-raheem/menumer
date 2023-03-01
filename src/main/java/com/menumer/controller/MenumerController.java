package com.menumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class MenumerController {

    @GetMapping("/testing")
    public List<String> getItems(@RequestParam(value = "items") String items){
        System.out.println(items);
        return Arrays.asList("olajide","testing");
    }
}
