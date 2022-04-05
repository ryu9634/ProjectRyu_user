package com.example.project.repository;

import com.example.project.model.entity.Qna;
import com.example.project.model.entity.Zzim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZzimRepository extends JpaRepository<Zzim, Long> {
    List<Zzim> findAllByUserIdx(Long userIdx);
    Long deleteAllByUserIdx(Long userIdx);
    Page<Zzim> findAllByUserIdx(Long userIdx , Pageable pageable);
}
