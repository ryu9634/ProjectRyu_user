package com.example.project.controller.api;

import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.entity.Notice;
import com.example.project.repository.NoticeRepository;
import com.example.project.service.GoodsApiLogicService;
import com.example.project.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Enumerated;
import java.util.List;


@Slf4j
@RequestMapping("/www.duoback.co.kr")
@Controller
public class BoardController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;


    @GetMapping("/indexffe2")
    public String indexffe21(Model model ,@PageableDefault(page = 0 , size = 7 , sort = "ntIdx") Pageable pageable) {
        Page<Notice> list = noticeService.boardlist_page(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());

        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("list" , list);
        return "pages/www.duoback.co.kr/board/indexffe2";
    }

    @GetMapping("/find_notice")
    public String search (@RequestParam(value = "keyword") String keyword , Model model){
        List<NoticeDTO> noticeList = noticeService.search_notice(keyword);
        model.addAttribute("list" , noticeList);

        return "pages/www.duoback.co.kr/board/indexffe2";
    }

    @GetMapping("/index4fcb")
    public String index4fcb() {
        return "pages/www.duoback.co.kr/board/index4fcb";
    }

    @GetMapping("/indexf6aa")
    public String indexf6aa() {
        return "pages/www.duoback.co.kr/board/indexf6aa";
    }

    @GetMapping("/index0f52")
    public String index0f52() {
        return "pages/www.duoback.co.kr/board/index0f52";}

    @GetMapping("/viewe3f8/{ntIdx}")
    public String viewe3f8(Model model , @PathVariable(name = "ntIdx") Long ntIdx) {
        NoticeDTO noticeDTOList = noticeService.read(ntIdx);
        model.addAttribute("noticeList" , noticeDTOList);
        return "pages/www.duoback.co.kr/board/viewe3f8";
    }

    @GetMapping("/writee50a")
    public String writee50a() {
        return "pages/www.duoback.co.kr/board/writee50a";
    }

}