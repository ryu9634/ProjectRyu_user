package com.example.project.controller.api;

import com.example.project.model.DTO.ReviewDTO;
import com.example.project.model.entity.Review;
import com.example.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;


    /* @PostMapping("/new/{userIdx}")

    public void create(@PathVariable Long userIdx, @RequestBody ReviewDTO reviewDTO){

      reviewService.create(userIdx, reviewDTO);

    }*/
    @PostMapping("/new")

    public void reviewCreate(@RequestBody ReviewDTO reviewDTO){
        reviewService.create(reviewDTO);
       // HttpServletRequest request;
        //String referer = request.getHeader("Referer");

       // reviewService.create(get);
    }

    @GetMapping("/reviewlist/{userIdx}")
    public List<ReviewDTO> reviewlist(@PathVariable Long userIdx){
        List<ReviewDTO> reviewDTOList = reviewService.getReviewList(userIdx);
        return reviewDTOList;
    }

    @PostMapping("/delete/{rvIdx}")
    public void delete(@PathVariable Long rvIdx){
        reviewService.delete(rvIdx);
    }

}
