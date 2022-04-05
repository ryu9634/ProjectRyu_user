package com.example.project.controller.api;

import com.example.project.model.DTO.OrderDTO;
import com.example.project.model.DTO.RegistAddressDTO;
import com.example.project.model.entity.*;
import com.example.project.model.enumclass.OrderStatus;
import com.example.project.model.network.Header;
import com.example.project.model.network.request.UserApiRequest;
import com.example.project.service.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/www.duoback.co.kr")
@Controller
public class MypageController {



    @Autowired
    private HttpSession session;

    @Autowired
    private  OrderService orderService;

    @Autowired
    private UserApiLogicService userApiLogicService;

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
    private QnaService qnaService;

    @GetMapping("/mypage_qna_write")
    public String mypage_qna_write(Model model) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_qna_write";
    }

    @GetMapping("/mypage_review_write/{gdIdx}")
    public String mypage_review_write(Model model , @PathVariable Long gdIdx) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("goods", goodsApiLogicService.read(gdIdx));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_review_write";
    }

    @GetMapping("/mypage_zzim")
    public String mypage_zzim(Model model , @PageableDefault(page = 0, size = 5 , sort = "userIdx" , direction = Sort.Direction.DESC) Pageable pageable) {
        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        Page<Zzim> list = zzimService.zzim_list(user.getUserIdx() , pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("zzimList" , list);

        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
//        model.addAttribute("zzimlist" , zzimService.getZzimList(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_zzim";
    }

    @GetMapping("/mypage_order")
    public String mypage_order(Model model , @PageableDefault(page = 0 , size = 5 , sort = "userIdx" , direction = Sort.Direction.DESC)Pageable pageable) {
        if(session.getAttribute("userid")==null){
        return "pages/www.duoback.co.kr/member/login";
    } else {
            User user = (User)session.getAttribute("user");

            Page<Order> list = orderService.orderlist_page(user.getUserIdx() , pageable);
            int nowPage = list.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage - 4 ,1);
            int endPage = Math.min(nowPage + 5 , list.getTotalPages());
            model.addAttribute("nowPage" , nowPage);
            model.addAttribute("startPage" , startPage);
            model.addAttribute("endPage" , endPage);
            model.addAttribute("orderList" , list);
            model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
            model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
            model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
            model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
            return "pages/www.duoback.co.kr/mypage/mypage_order";
        }
    }

    @GetMapping("/mypage_cancle")
    public String mypage_cancle(Model model , @PageableDefault(size = 5 , page = 0 , sort = "userIdx" , direction = Sort.Direction.DESC) Pageable pageable) {
        if(session.getAttribute("userid")==null){
        return "pages/www.duoback.co.kr/member/login";
    }
        User user = (User)session.getAttribute("user");
        Page<Order> list = orderService.cancle_page(user.getUserIdx() , pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("cancelList" , list);

        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
//        model.addAttribute("cancelList",orderService.getCanceledList(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_cancle";
    }

    @GetMapping("/mypage_return")
    public String return_page(Model model , @PageableDefault(size = 5 , page = 0 , sort = "userIdx" , direction = Sort.Direction.DESC) Pageable pageable) {

        if(session.getAttribute("userid")==null){
        return "pages/www.duoback.co.kr/member/login";

        }

        User user = (User)session.getAttribute("user");
        Page<Order> list = orderService.return_page(user.getUserIdx() , pageable );

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("returnList" , list);

        //        model.addAttribute("returnList" , orderService.getExchangeReturnList(user.getUserIdx()));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_return";



    }

    @GetMapping("/mypage_coupon")
    public String mypage_coupon(Model model , @PageableDefault(size = 5 , page = 0 , sort = "userIdx" , direction = Sort.Direction.DESC) Pageable pageable) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        Page<Coupon> list = couponService.coupon_list(user.getUserIdx() , pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("coupon" , list);

        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
//        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_coupon";
    }

    @GetMapping("/mypage_dpoint")
    public String mypage_dpoint(Model model) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_dpoint";
    }

    @GetMapping("/mypage_review")
    public String mypage_review(Model model , @PageableDefault(page = 0 , size = 5 , sort = "userIdx" , direction = Sort.Direction.ASC) Pageable pageable) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");

        Page<Review> list = reviewService.review_list(user.getUserIdx() , pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("review" , list);

        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
//        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_review";
    }

    @GetMapping("/mypage_qna")
    public String mypage_qna(Model model) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }

        User user = (User)session.getAttribute("user");
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        model.addAttribute("qnalist" , qnaService.getBoardList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_qna";
    }

    @GetMapping("/mypage_recentGoods")
    public String mypage_recentGoods(Model model , @PageableDefault(size = 5 , page = 0 , sort = "userIdx" ,direction = Sort.Direction.DESC)Pageable pageable) {
        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");

        Page<Recent> list = recentService.recents_page(user.getUserIdx() , pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("recentList" , list);
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("recent" , recentService.getRecentList(user.getUserIdx()));
        model.addAttribute("goodslist" , goodsApiLogicService.read(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_recentGoods";
    }

    @GetMapping("/mypage_myinfo")
    public String mypage_myinfo(Model model) {
        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_myinfo";
    }

    @RequestMapping(value="/mypage_delivery_address", method = {RequestMethod.GET, RequestMethod.POST})
    public String mypage_delivery_address(Model model) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }

        User user = (User)session.getAttribute("user");
        model.addAttribute("addressList",registAddressService.getAddressList(user.getUserIdx()));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_delivery_address";
    }

    @RequestMapping(value="/address/{rgaIdx}", method = {RequestMethod.GET, RequestMethod.POST})
    public String address(Model model, @PathVariable Long rgaIdx) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("addressList",registAddressService.getAddressList(user.getUserIdx()));
        model.addAttribute("address",registAddressService.read(rgaIdx));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/address";
    }


    @PostMapping({"/deleteAdd/{rgaIdx}"})
    public String deleteAdd(@PathVariable(name = "rgaIdx") Long rgaIdx) {
        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        registAddressService.delete(rgaIdx);
        return "redirect:/www.duoback.co.kr/mypage_delivery_address";
    }


    @GetMapping("/mypage_withdrawal")
    public String mypage_withdrawal(Model model) {

        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        return "pages/www.duoback.co.kr/mypage/mypage_withdrawal";
    }

    @DeleteMapping({"/delete/{userIdx}"})
    public String memberDelete(@PathVariable(name = "userIdx") Long userIdx) {
        if(session.getAttribute("userid")==null){
            return "pages/www.duoback.co.kr/member/login";
        }
        this.userApiLogicService.delete(userIdx);
        session.invalidate();
        return "redirect:/www.duoback.co.kr";
    }

    @PostMapping("/deleteAdd/delete")
    public List<String> deleteSubmit(@RequestBody List<String> arr){
        registAddressService.deleteBoard(arr);
        return arr;
    }

    @RequestMapping(value="/updateAdd/{rgaIdx}", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(@RequestBody RegistAddressDTO registAddressDTO, @PathVariable Long rgaIdx,Model model){
        registAddressService.update(registAddressDTO, rgaIdx);
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("dpoint", dpointService.getDpointList(user.getUserIdx()));
        model.addAttribute("coupon", couponService.getCouponList(user.getUserIdx()));
        model.addAttribute("review",reviewService.getReviewList(user.getUserIdx()));
        return "redirect:/www.duoback.co.kr/mypage_delivery_address";
    }


}
