package com.example.project.controller.api;

import com.example.project.model.entity.Cart;
import com.example.project.model.entity.User;
import com.example.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/www.duoback.co.kr")
@Controller
public class OrderController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RegistAddressService registAddressService;

    @Autowired
    private DpointService dpointService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GoodsApiLogicService goodsApiLogicService;

    @Autowired
    private RecentService recentService;

    @Autowired
    private ZzimService zzimService;

    @Autowired
    private  CartApiLogicService cartApiLogicService;

    @GetMapping("/cart")
    public String cart(Model model) {

        User user = (User)session.getAttribute("user");
        model.addAttribute("goodList" , goodsApiLogicService.read(user.getUserIdx()));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("cart" , cartApiLogicService.getCartList(user.getUserIdx()));


        return "pages/www.duoback.co.kr/order/cart";
    }

    @GetMapping("/indexf956")
    public String indexf956() {
        return "pages/www.duoback.co.kr/page/indexf956";
    }

    @GetMapping("/index6a5b")
    public String index6a5b() {
        return "pages/www.duoback.co.kr/page/index6a5b";
    }

    @GetMapping("/index9f24")
    public String index9f24() {
        return "pages/www.duoback.co.kr/page/index9f24";
    }

    @GetMapping("/index0fcd")
    public String index0fcd() {
        return "pages/www.duoback.co.kr/page/index0fcd";
    }



}
