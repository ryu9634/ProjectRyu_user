package com.example.project.repository;

import com.example.project.model.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByNtTitleContaining(String keyword);
    Page<Notice> findAllByOrderByNtRegdateDesc(Pageable pageable);
}
