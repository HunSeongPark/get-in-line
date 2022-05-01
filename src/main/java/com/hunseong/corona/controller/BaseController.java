package com.hunseong.corona.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Hunseong on 2022/05/02
 */
@Controller
public class BaseController {

    @GetMapping("/")
    public String root() {
        return "redirect:/events";
    }
}
