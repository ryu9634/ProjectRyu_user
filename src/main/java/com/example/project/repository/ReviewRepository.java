package com.example.project.repository;

import com.example.project.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserIdx(Long userIdx);
    List<Review> findAllByGoods_GdIdx(Long gdIdx);
    Page<Review> findAllByUserIdx(Long userIdx , Pageable pageable);
    Page<Review> findAllByGoods_GdIdx(Long gdIdx, Pageable pageable);
}
