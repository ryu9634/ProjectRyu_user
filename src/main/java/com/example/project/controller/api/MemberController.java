package com.example.project.controller.api;

import com.example.project.model.entity.User;
import com.example.project.service.CartApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/www.duoback.co.kr")
public class MemberController {

    @Autowired
    private CartApiLogicService cartApiLogicService;

    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String duoback(Model model) {
        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/duoback";
        }

        User user = (User)session.getAttribute("user");
        model.addAttribute("cartList",cartApiLogicService.getCartList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/duoback";
    }

    @GetMapping("/agreementb621") //간편회원가입추가
    public String duoback_agreementb621() {
        return "pages/www.duoback.co.kr/member/agreementb621";
    }

    @GetMapping("/agreement")
    public String duoback_agreement() {
        return "pages/www.duoback.co.kr/member/agreement";
    }

    @GetMapping("/bulkbuy")
    public String bulkbuy() {
        return "pages/www.duoback.co.kr/member/bulkbuy";
    }
}
