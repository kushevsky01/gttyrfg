package com.example.eventsmap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для отображения главной страницы
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
}
