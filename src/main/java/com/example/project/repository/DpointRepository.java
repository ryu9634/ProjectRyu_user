package com.example.project.repository;

import com.example.project.model.entity.Dpoint;
import com.example.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DpointRepository extends JpaRepository<Dpoint, Long> {
    Dpoint findByUserIdx(Long userIdx);
    List<Dpoint> findAllByUserIdx(Long userIdx);
}
