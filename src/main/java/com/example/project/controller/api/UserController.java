package com.example.project.controller.api;

import com.example.project.controller.CrudController;
import com.example.project.model.entity.*;
import com.example.project.model.network.Header;
import com.example.project.model.network.request.UserApiRequest;
import com.example.project.model.network.response.UserApiResponse;
import com.example.project.repository.*;
import com.example.project.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController //json으로 주고 받는 컨트롤러
@RequestMapping("/duoback")
@RequiredArgsConstructor
public class UserController extends CrudController<UserApiRequest, UserApiResponse, User> {

    private final UserApiLogicService userApiLogicService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RegistAddressRepository registAddressRepository;
    @Autowired
    private DpointRepository dpointRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ZzimRepository zzimRepository;
    @Autowired
    private RecentRepository recentRepository;
    @Autowired
    private QnaRepository qnaRepository;


    /*
    {
        "transaction_time":"2022-03-04",
            "resultCode":"ok",
            "description":"ok",
            "data":{
                "userUserid":"banana",
                "userUserpw":"2222",
                "userName":"반하나",
                "userHp":"010-1111-2222",
                "userEmail":"banana@banana.com",
                "userSns":"kakao",
                "userZipcode":"12345",
                "userAddress1":"서울 서초구 역삼동",
                "userAddress2":"코리아아이티학원"
    }
    }
    */

    @Override
    @PostMapping("/new") //http://localhost:8080/test (post)
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("/update")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public Header<UserApiResponse> delete(@PathVariable(name="id") Long id) {
        zzimRepository.delete(Zzim.builder().userIdx(id).build());
        qnaRepository.delete(Qna.builder().userIdx(id).build());
        recentRepository.delete(Recent.builder().userIdx(id).build());
        orderRepository.delete(Order.builder().userIdx(id).build());
        couponRepository.delete(Coupon.builder().userIdx(id).build());
        reviewRepository.delete(Review.builder().userIdx(id).build());
        dpointRepository.delete(Dpoint.builder().userIdx(id).build());
        registAddressRepository.delete(RegistAddress.builder().userIdx(id).build());
        cartRepository.delete(Cart.builder().userIdx(id).build());
        log.info(id+"삭제");
        return userApiLogicService.delete(id);

    }

//    @GetMapping("") // http://localhost:8080/
//    public Header<List<UserApiResponse>> search(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        return userApiLogicService2.search(pageable);
//    }
    @GetMapping("/list")
    public Header<List<UserApiResponse>> list(){
        return userApiLogicService.list();
    }

}
