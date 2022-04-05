package com.example.project.repository;

import com.example.project.model.entity.Coupon;
import com.example.project.model.entity.Dpoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByUserIdx(Long userIdx);
    Page<Coupon> findAllByUserIdx(Long userIdx , Pageable pageable);
    Coupon findByUserIdx(Long userIdx);
}
