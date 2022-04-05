package com.example.project.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/www.duoback.co.kr")
@Controller
public class ServiceController {
    @GetMapping("/cs_as")
    public String cs_as() {
        return "pages/www.duoback.co.kr/service/cs_as";
    }

    @GetMapping("/cs_exchange")
    public String cs_exchange() {
        return "pages/www.duoback.co.kr/service/cs_exchange";
    }

    @GetMapping("/cs_goods")
    public String cs_goods() {
        return "pages/www.duoback.co.kr/service/cs_goods";
    }

    @GetMapping("/cs_homepage")
    public String cs_homepage() {
        return "pages/www.duoback.co.kr/service/cs_homepage";
    }

    @GetMapping("/cs_dpoint")
    public String cs_dpoint() {
        return "pages/www.duoback.co.kr/service/cs_dpoint";
    }

    @GetMapping("/cs")
    public String cs() {
        return "pages/www.duoback.co.kr/service/cs";
    }

    @GetMapping("/location")
    public String location() {
        return "pages/www.duoback.co.kr/service/location";
    }

    @GetMapping("/agreement_service")
    public String agreement_service() {return "pages/www.duoback.co.kr/service/agreement";}

    @GetMapping("/privacy")
    public String privacy() {return "pages/www.duoback.co.kr/service/privacy";}
}
