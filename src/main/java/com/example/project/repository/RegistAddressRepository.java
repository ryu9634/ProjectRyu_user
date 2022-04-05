package com.example.project.repository;

import com.example.project.model.entity.RegistAddress;
import com.example.project.model.enumclass.AddressStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistAddressRepository extends JpaRepository<RegistAddress, Long> {
    List<RegistAddress> findAllByUserIdx(Long userIdx);
    RegistAddress deleteAllByUserIdx(Long userIdx);
    Optional<RegistAddress> findByRgaStatus(AddressStatus addressStatus);
    Optional<RegistAddress> findByRgaIdx(Long rgaIdx);
}
