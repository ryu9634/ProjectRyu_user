//package com.example.project.controller.api;
//
//import com.example.project.model.DTO.FaqDTO;
//import com.example.project.model.DTO.NoticeDTO;
//import com.example.project.service.FaqService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@RequestMapping("/faq")
//@RestController
//public class FaqController {
//
//    @Autowired
//    private FaqService faqService;
//    HttpSession session;
//    // 글작성
//    //http://localhost:8080/faq/write
//    @PostMapping("/write")
//    public void write(@RequestBody FaqDTO faqDTO)  {
//        faqService.create(faqDTO);
//    }
//
//    // 게시판 목록
//    @GetMapping("/list")
//    public String list(Model model){
//        if(session.getAttribute("userid")==null){
//            return "pages/www.duoback.co.kr/duoback";
//        }
//        List<FaqDTO> faqDTOList = faqService.getBoardList();
//        model.addAttribute("faqDTOList",faqDTOList);
//        return "faq/list";
//    }
//}
