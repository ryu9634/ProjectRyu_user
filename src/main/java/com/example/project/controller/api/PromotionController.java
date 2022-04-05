package com.example.project.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/www.duoback.co.kr")
@Controller
public class PromotionController {

    @GetMapping("/as3year")
    public String as3year() {
        return "pages/www.duoback.co.kr/promotion/as3year";
    }

    @GetMapping("/event")
    public String event() {
        return "pages/www.duoback.co.kr/promotion/event";
    }
}
