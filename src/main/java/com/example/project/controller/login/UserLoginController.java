package com.example.project.controller.login;

import com.example.project.controller.Test;
import com.example.project.login.LoginService;
import com.example.project.model.entity.Coupon;
import com.example.project.model.entity.Dpoint;
import com.example.project.model.entity.User;
import com.example.project.repository.CouponRepository;
import com.example.project.repository.DpointRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/www.duoback.co.kr")
public class UserLoginController  {


    private final LoginService loginService;



    @Autowired
    UserRepository userRepository;
    DpointRepository dpointRepository;
    CouponRepository couponRepository;


    @Autowired
    private HttpSession session;


    @GetMapping("/login")
    public String duoback_login() {
        return "pages/www.duoback.co.kr/member/login";
    }


    // post 방식으로 로그인
    @PostMapping("/login")
    public String postMain(@RequestParam(name = "userUserid") String userUserid, @RequestParam(name = "userUserpw") String userUserpw) {

        User user =  userRepository.findByUserUserid(userUserid);
//        Dpoint dpoint = dpointRepository.findByUserIdx(user.getUserIdx());
//        Coupon coupon = couponRepository.findByUserIdx(user.getUserIdx());
        log.info("user id::" + userUserid);
        log.info("user pw::" + userUserpw);
        String userid = userUserid;
        String userpw = userUserpw;

        if (loginService.login(userid, userpw)) {
            session.setAttribute("userid", userid);
            session.setAttribute("user", user);
//            session.setAttribute("dpoint", dpoint);
//            session.setAttribute("coupon", coupon);


            log.info("user id::" + userid);
            log.info("user pw::" + userpw);
            log.info("user ::" + user);


            return "redirect:/www.duoback.co.kr"; //메인
        } else {
            return "pages/www.duoback.co.kr/member/loginfail"; //다시 로그인

        }
    }
    //로그인 실패 페이지
    @GetMapping("/loginfail")
    public String duoback_loginfail() {
        return "pages/www.duoback.co.kr/member/login";
    }



    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        log.info("user id::" + session.getAttribute("userid"));
        log.info("user pw::" + session.getAttribute("user"));

        return "pages/www.duoback.co.kr/member/login";
    }



    //휴대폰 인증 컨트롤러
    @PostMapping("/message")
    public @ResponseBody String joinMessage( String phoneNumber,  HttpServletRequest request){
        Random rand = new Random();
        String numStr = "";
        for (int i =0; i<6; i++){
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
            log.info(numStr+"");
        }
        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        certifiedPhoneNumber(phoneNumber,numStr);

        return numStr;
    }
    //휴대폰 인증 서비스 메소드
    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCS4OMC4FO9KSOGP"; //인증키
        String api_secret = "AHQMIJPIVENTHAEKSX4QUWOCQLARCTHA"; //인증 PW
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "010640458686");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "휴대폰인증 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }
}
