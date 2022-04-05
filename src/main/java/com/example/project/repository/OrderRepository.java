package com.example.project.repository;

import com.example.project.model.DTO.OrderDTO;
import com.example.project.model.entity.Order;
import com.example.project.model.enumclass.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdx(Long userIdx);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    Page<Order> findAllByUserIdxAndOrderStatus(Long userIdx , OrderStatus orderStatus , Pageable pageable);
    Optional<Order> findTopByUserIdxOrderByOrderRegdateDesc(Long userIdx);
    Page<Order> findAllByUserIdx(Long userIdx , Pageable pageable);
    List<Order> findAllByUserIdxAndOrderStatus(Long userIdx, OrderStatus orderStatus);
    Page<Order> findAllByUserIdxAndOrderStatusOrOrderStatusOrOrderStatusOrOrderStatus( Long userIdx, OrderStatus orderStatus, OrderStatus orderStatus1, OrderStatus orderStatus2, OrderStatus orderStatus3, Pageable pageable);
//    Page<Order> findAllByUserIdxAndOrderStatusIn(Long userIdx , List<OrderDTO> orderStatuses , Pageable pageable);


}
