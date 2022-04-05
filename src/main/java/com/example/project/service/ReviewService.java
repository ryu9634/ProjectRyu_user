package com.example.project.service;

import com.example.project.model.DTO.ReviewDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Review;
import com.example.project.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Review create(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .userIdx(reviewDTO.getUserIdx())
                .rvContent(reviewDTO.getRvContent())
                .rvRegdate(LocalDateTime.now())
                .rvImg(reviewDTO.getRvImg())
                .rvStar(reviewDTO.getRvStar())
                .rvTitle(reviewDTO.getRvTitle())
                .goods(Goods.builder()
                        .gdIdx(reviewDTO.getGoods().getGdIdx())
                        .build())
                .build();
        Review newReview = reviewRepository.save(review);
        return newReview;


        /*
         public Review create(Long userIdx, ReviewDTO reviewDTO){
        Review review = Review.builder()
                .userIdx(userIdx)
                .rvContent(reviewDTO.getRvContent())
                .rvRegdate(LocalDateTime.now())
                .rvImg(reviewDTO.getRvImg())
                .rvStar(reviewDTO.getRvStar())
                .rvTitle(reviewDTO.getRvTitle())
                .build();
        Review newReview = reviewRepository.save(review);
        return newReview;
         */
    }

    public Page<Review> review_list(Long userIdx , Pageable pageable){
        return reviewRepository.findAllByUserIdx(userIdx, pageable);
    }

    @Transactional
    public List<ReviewDTO> getReviewList(Long userIdx){
        List<Review> reviewList = reviewRepository.findAllByUserIdx(userIdx);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        for(Review review : reviewList){
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .rvIdx(review.getRvIdx())
                    .userIdx(review.getUserIdx())
                    .rvContent(review.getRvContent())
                    .rvTitle(review.getRvTitle())
                    .rvStar(review.getRvStar())
                    .rvImg(review.getRvImg())
                    .rvRegdate(review.getRvRegdate())
                    .goods(Goods.builder()
                            .gdName(review.getGoods().getGdName())
                            .gdImg(review.getGoods().getGdImg())
                            .gdIdx(review.getGoods().getGdIdx())
                            .build())
                    .build();
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }

    @Transactional
    public List<ReviewDTO> getReviewListGoods(Long gdIdx){
        List<Review> reviewList = reviewRepository.findAllByGoods_GdIdx(gdIdx);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        for(Review review : reviewList){
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .rvIdx(review.getRvIdx())
                    .userIdx(review.getUserIdx())
                    .rvContent(review.getRvContent())
                    .rvTitle(review.getRvTitle())
                    .rvStar(review.getRvStar())
                    .rvImg(review.getRvImg())
                    .rvRegdate(review.getRvRegdate())
                    .goods(Goods.builder()
                            .gdName(review.getGoods().getGdName())
                            .gdImg(review.getGoods().getGdImg())
                            .gdIdx(review.getGoods().getGdIdx())
                            .build())
                    .build();
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }

    public Page<Review>  Review_list(Long gdIdx , Pageable pageable){
        return reviewRepository.findAllByGoods_GdIdx(gdIdx, pageable);
    }

    @Transactional
    public List<ReviewDTO> list(){
        List<Review> reviewList = reviewRepository.findAll();
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        for(Review review : reviewList){
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .rvIdx(review.getRvIdx())
                    .userIdx(review.getUserIdx())
                    .rvContent(review.getRvContent())
                    .rvTitle(review.getRvTitle())
                    .rvStar(review.getRvStar())
                    .rvImg(review.getRvImg())
                    .rvRegdate(review.getRvRegdate())
                    .goods(Goods.builder()
                            .gdName(review.getGoods().getGdName())
                            .gdImg(review.getGoods().getGdImg())
                            .gdIdx(review.getGoods().getGdIdx())
                            .build())
                    .build();
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }

    @Transactional
    public ReviewDTO read(Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        Review review = reviewOptional.get();
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .rvIdx(review.getRvIdx())
                .userIdx(review.getUserIdx())
                .rvContent(review.getRvContent())
                .rvTitle(review.getRvTitle())
                .rvStar(review.getRvStar())
                .rvImg(review.getRvImg())
                .rvRegdate(review.getRvRegdate())
                .goods(Goods.builder()
                        .gdName(review.getGoods().getGdName())
                        .gdImg(review.getGoods().getGdImg())
                        .gdIdx(review.getGoods().getGdIdx())
                        .build())
                .build();
        return reviewDTO;

        }

    @Transactional
    public void delete(Long id){
        reviewRepository.deleteById(id);
    }


}

