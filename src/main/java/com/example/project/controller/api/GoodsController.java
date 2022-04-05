package com.example.project.controller.api;


import com.example.project.model.DTO.DpointDTO;
import com.example.project.model.DTO.OrderDTO;
import com.example.project.model.DTO.RecentDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Notice;
import com.example.project.model.entity.Review;
import com.example.project.model.entity.User;
import com.example.project.model.network.Header;
import com.example.project.model.network.request.GoodsApiRequest;
import com.example.project.model.network.response.GoodsApiResponse;
import com.example.project.repository.RecentRepository;
import com.example.project.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/www.duoback.co.kr")
public class GoodsController {

    @Autowired
    private CartApiLogicService cartApiLogicService;

    @Autowired
    private GoodsApiLogicService goodsApiLogicService;

    @Autowired
    private RegistAddressService registAddressService;

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DpointService dpointService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/catalog5366")
    public String catalog5366() {
        return "pages/www.duoback.co.kr/goods/catalog5366";
    }

    @GetMapping("/catalogb0e9")
    public String catalogb0e9() {
        return "pages/www.duoback.co.kr/goods/catalogb0e9";
    }

    @GetMapping("/catalog762d")
    public String catalog762d(Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("goodsList", goodsApiLogicService.getGoodsList());
        return "pages/www.duoback.co.kr/goods/catalog762d";
    }

    @GetMapping("/catalog117e")
    public String catalog117e(Model model) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("goodsList" , goodsApiLogicService.getGoodsList());
        return "pages/www.duoback.co.kr/goods/catalog117e";
    }

    @GetMapping("/catalog3bca")
    public String catalog3bca() {
        return "pages/www.duoback.co.kr/goods/catalog3bca";
    }

    @GetMapping("/brand_main")
    public String brand_main() {
        return "pages/www.duoback.co.kr/goods/brand_main";
    }

    @GetMapping("/catalog483b")
    public String catalog483b() {
        return "pages/www.duoback.co.kr/goods/catalog483b";
    }



//    @GetMapping("/catalog617e")
//    public String catalog617e(Model model) {
//        List<GoodsDTO>goodsDTOList = goodsApiLogicService.getGoodsList();
//        model.addAttribute("goodsList" , goodsDTOList);
//        return "pages/www.duoback.co.kr/goods/catalog617e";
//
//    }


    @GetMapping("/catalog9335")
    public String catalog9335() {
        return "pages/www.duoback.co.kr/goods/catalog9335";
    }


    @GetMapping("/catalog617e")
    public String catalog617e(Model model) {
        List<GoodsApiResponse> goodsApiResponseList = goodsApiLogicService.getGoodsList();
        String name = "sad";
        model.addAttribute("goodsList", goodsApiResponseList);
        return "pages/www.duoback.co.kr/goods/catalog617e";
        // 왜 안되는 건감...

    }

    @GetMapping("/catalog33b6")
    public String catalog33b6(Model model){
        model.addAttribute("goodsList", goodsApiLogicService.getGoodsList());
        return "pages/www.duoback.co.kr/goods/catalog33b6";
    }



    @GetMapping("/goods/proinfo/{gdIdx}")
    public String proinfo(Model model , @PathVariable(name="gdIdx") Long gdIdx , @PageableDefault(page = 0 , size = 5 , direction = Sort.Direction.DESC)Pageable pageable){

        Page<Review> list = reviewService.Review_list(gdIdx , pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());


        User user = (User)session.getAttribute("user");
        model.addAttribute("goodsList" , goodsApiLogicService.read(gdIdx));
        model.addAttribute("user" , userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("cartList",cartApiLogicService.getCartList(user.getUserIdx()));
        model.addAttribute("reviewsize",reviewService.getReviewListGoods(gdIdx));
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("review" , list);

        return "pages/www.duoback.co.kr/goods/proinfo";
    }

    @GetMapping("/orderdetail/{gdIdx}")
    public String orderdetail(Model model ,@PathVariable(name="gdIdx") Long gdIdx){
        User user = (User)session.getAttribute("user");
        model.addAttribute("goods" , goodsApiLogicService.read(gdIdx));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
//        model.addAttribute("addressList", registAddressService.getAddressList(user.getUserIdx()));
        return "pages/www.duoback.co.kr/goods/orderdetail";
    }



    @RequestMapping(value="/fin/{gdIdx}", method = {RequestMethod.GET, RequestMethod.POST})
    public String fin(Model model, @PathVariable(name="gdIdx") Long gdIdx){
        User user = (User)session.getAttribute("user");
        model.addAttribute("goods" , goodsApiLogicService.read(gdIdx));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        model.addAttribute("order",orderService.read2(user.getUserIdx()));
        return "pages/www.duoback.co.kr/goods/fin";
    }

    @RequestMapping(value="/pay/{gdIdx}", method = {RequestMethod.GET, RequestMethod.POST})
    public String pay(Model model, @PathVariable(name="gdIdx") Long gdIdx){
        User user = (User)session.getAttribute("user");
        model.addAttribute("goods" , goodsApiLogicService.read(gdIdx));
        model.addAttribute("user", userApiLogicService.read(user.getUserIdx()));
        return "pages/www.duoback.co.kr/goods/pay";
    }

    @RequestMapping(value="/ordercreate/{userIdx}", method = {RequestMethod.POST})
    public void orderCreate(@PathVariable Long userIdx, @RequestBody OrderDTO orderDTO){
        orderService.create(userIdx, orderDTO);
    }

    @RequestMapping(value="/dpointcreate/{userIdx}", method = {RequestMethod.POST})
    public void dpointcreate(@PathVariable Long userIdx, @RequestBody DpointDTO dpointDTO){
        dpointService.create(userIdx, dpointDTO);
    }

    @PostMapping("/buy/{gdIdx}")
    public void buy(@PathVariable Long gdIdx){
        goodsApiLogicService.buy(gdIdx);
    }


    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<GoodsApiResponse> goodsApiResponseList = goodsApiLogicService.searchGoods(keyword);
        model.addAttribute("goodsList", goodsApiResponseList);

        return "pages/www.duoback.co.kr/search";
    }
}
