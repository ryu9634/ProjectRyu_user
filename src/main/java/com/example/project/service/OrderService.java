package com.example.project.service;

import com.example.project.model.DTO.OrderDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Order;
import com.example.project.model.enumclass.OrderStatus;
import com.example.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private static AtomicInteger count = new AtomicInteger(0);

    @Transactional
    public Order create(Long userIdx, OrderDTO orderDTO){

        LocalDateTime now = LocalDateTime.now();
        Order order = Order.builder()
                .userIdx(userIdx)
                .orderSeller("본사")
                .orderRegdate(now)
                .orderStatus(OrderStatus.PAY)
                .orderNum(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+userIdx)
                .orderZipcode(orderDTO.getOrderZipcode())
                .orderAddress1(orderDTO.getOrderAddress1())
                .orderAddress2(orderDTO.getOrderAddress2())
                .orderTel1(orderDTO.getOrderTel1())
                .orderTel2(orderDTO.getOrderTel2())
                .orderRequest(orderDTO.getOrderRequest())
                .orderRevname(orderDTO.getOrderRevname())
                .goods(Goods.builder()
                        .gdIdx(orderDTO.getGoods().getGdIdx()).build())
                .build();
        Order newOrder = orderRepository.save(order);
        return newOrder;
    }
    @Transactional
    public OrderDTO read(Long id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order order = orderOptional.get();
        OrderDTO orderDTO = OrderDTO.builder()
                .orderIdx(order.getOrderIdx())
                .orderNum(order.getOrderNum())
                .orderStatus(order.getOrderStatus())
                .orderSeller(order.getOrderSeller())
                .orderRegdate(order.getOrderRegdate())
                .orderZipcode(order.getOrderZipcode())
                .orderAddress1(order.getOrderAddress1())
                .orderAddress2(order.getOrderAddress2())
                .orderTel1(order.getOrderTel1())
                .orderTel2(order.getOrderTel2())
                .orderRequest(order.getOrderRequest())
                .orderRevname(order.getOrderRevname())
                .userIdx(order.getUserIdx())
                .goods(Goods.builder()
                        .gdIdx(order.getGoods().getGdIdx())
                        .gdName(order.getGoods().getGdName())
                        .gdImg(order.getGoods().getGdImg())
                        .build())
                .build();
        return orderDTO;
    }

    @Transactional
    public List<OrderDTO> list(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .userIdx(order.getUserIdx())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    public Page<Order> orderlist_page(Long userIdx , Pageable pageable){
        return orderRepository.findAllByUserIdx(userIdx , pageable);
    }

    public Page<Order> cancle_page(Long userIdx , Pageable pageable ){
        return orderRepository.findAllByUserIdxAndOrderStatus(userIdx ,OrderStatus.CANCELED ,pageable);
    }

    public Page<Order> return_page(Long userIdx , Pageable pageable){
        return orderRepository.findAllByUserIdxAndOrderStatusOrOrderStatusOrOrderStatusOrOrderStatus(userIdx , OrderStatus.RETURNED ,OrderStatus.EXCHANGED , OrderStatus.EXCHANGING , OrderStatus.RETURNING , pageable);
    }

//    @Transactional
//    public Page<Order> return_page(Long userIdx, Pageable pageable){
//        List<Order> orderList1 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.EXCHANGING);
//        List<Order> orderList2 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.EXCHANGED);
//        List<Order> orderList3 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.RETURNING);
//        List<Order> orderList4 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.RETURNED);
//        List<OrderDTO> orderDTOList = new ArrayList<>();
//
//        for(Order order : orderList1){
//            OrderDTO orderDTO = OrderDTO.builder()
//                    .userIdx(order.getUserIdx())
//                    .orderIdx(order.getOrderIdx())
//                    .orderSeller(order.getOrderSeller())
//                    .orderNum(order.getOrderNum())
//                    .orderStatus(order.getOrderStatus())
//                    .orderRegdate(order.getOrderRegdate())
//                    .orderZipcode(order.getOrderZipcode())
//                    .orderAddress1(order.getOrderAddress1())
//                    .orderAddress2(order.getOrderAddress2())
//                    .orderTel1(order.getOrderTel1())
//                    .orderTel2(order.getOrderTel2())
//                    .orderRequest(order.getOrderRequest())
//                    .orderRevname(order.getOrderRevname())
//                    .goods(Goods.builder()
//                            .gdIdx(order.getGoods().getGdIdx())
//                            .gdName(order.getGoods().getGdName())
//                            .gdImg(order.getGoods().getGdImg())
//                            .gdBrand(order.getGoods().getGdBrand())
//                            .build())
//                    .build();
//            orderDTOList.add(orderDTO);
//        }
//
//        for(Order order : orderList2){
//            OrderDTO orderDTO2 = OrderDTO.builder()
//                    .userIdx(order.getUserIdx())
//                    .orderIdx(order.getOrderIdx())
//                    .orderSeller(order.getOrderSeller())
//                    .orderNum(order.getOrderNum())
//                    .orderStatus(order.getOrderStatus())
//                    .orderRegdate(order.getOrderRegdate())
//                    .orderZipcode(order.getOrderZipcode())
//                    .orderAddress1(order.getOrderAddress1())
//                    .orderAddress2(order.getOrderAddress2())
//                    .orderTel1(order.getOrderTel1())
//                    .orderTel2(order.getOrderTel2())
//                    .orderRequest(order.getOrderRequest())
//                    .orderRevname(order.getOrderRevname())
//                    .goods(Goods.builder()
//                            .gdIdx(order.getGoods().getGdIdx())
//                            .gdName(order.getGoods().getGdName())
//                            .gdImg(order.getGoods().getGdImg())
//                            .gdBrand(order.getGoods().getGdBrand())
//                            .build())
//                    .build();
//            orderDTOList.add(orderDTO2);
//        }
//
//
//        for(Order order : orderList3){
//            OrderDTO orderDTO3 = OrderDTO.builder()
//                    .userIdx(order.getUserIdx())
//                    .orderIdx(order.getOrderIdx())
//                    .orderSeller(order.getOrderSeller())
//                    .orderNum(order.getOrderNum())
//                    .orderStatus(order.getOrderStatus())
//                    .orderRegdate(order.getOrderRegdate())
//                    .orderZipcode(order.getOrderZipcode())
//                    .orderAddress1(order.getOrderAddress1())
//                    .orderAddress2(order.getOrderAddress2())
//                    .orderTel1(order.getOrderTel1())
//                    .orderTel2(order.getOrderTel2())
//                    .orderRequest(order.getOrderRequest())
//                    .orderRevname(order.getOrderRevname())
//                    .goods(Goods.builder()
//                            .gdIdx(order.getGoods().getGdIdx())
//                            .gdName(order.getGoods().getGdName())
//                            .gdImg(order.getGoods().getGdImg())
//                            .gdBrand(order.getGoods().getGdBrand())
//                            .build())
//                    .build();
//            orderDTOList.add(orderDTO3);
//        }
//
//        for(Order order : orderList4){
//            OrderDTO orderDTO4 = OrderDTO.builder()
//                    .userIdx(order.getUserIdx())
//                    .orderIdx(order.getOrderIdx())
//                    .orderSeller(order.getOrderSeller())
//                    .orderNum(order.getOrderNum())
//                    .orderStatus(order.getOrderStatus())
//                    .orderRegdate(order.getOrderRegdate())
//                    .orderZipcode(order.getOrderZipcode())
//                    .orderAddress1(order.getOrderAddress1())
//                    .orderAddress2(order.getOrderAddress2())
//                    .orderTel1(order.getOrderTel1())
//                    .orderTel2(order.getOrderTel2())
//                    .orderRequest(order.getOrderRequest())
//                    .orderRevname(order.getOrderRevname())
//                    .goods(Goods.builder()
//                            .gdIdx(order.getGoods().getGdIdx())
//                            .gdName(order.getGoods().getGdName())
//                            .gdImg(order.getGoods().getGdImg())
//                            .gdBrand(order.getGoods().getGdBrand())
//                            .build())
//                    .build();
//            orderDTOList.add(orderDTO4);
//        }
//        return orderRepository.findAllByUserIdxAndOrderStatusOrOrderStatusOrOrderStatusOrOrderStatus(userIdx , orderList4 , orderList1 , orderList2 , orderList3 , pageable);
//    }

    @Transactional
    public List<OrderDTO> getOrderList(Long userIdx){
        List<Order> orderList = orderRepository.findAllByUserIdx(userIdx);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .gdBrand(order.getGoods().getGdBrand())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public void updateReady(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.READY);
        });
    }

    @Transactional
    public void updateSend(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.SEND);
        });
    }

    @Transactional
    public void updateFinish(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.FINISH);
        });
    }

    @Transactional
    public void updateCancel(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.CANCELED);
        });
    }

    @Transactional
    public void updateReturning(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.RETURNING);
        });
    }

    @Transactional
    public void updateReturned(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.RETURNED);
        });
    }



    @Transactional
    public List<OrderDTO> getCancelList(){
        List<Order> orderList = orderRepository.findAllByOrderStatus(OrderStatus.CANCELED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> getReturnList(){
        List<Order> orderList = orderRepository.findAllByOrderStatus(OrderStatus.RETURNED);
        List<Order> orderList1 = orderRepository.findAllByOrderStatus(OrderStatus.RETURNING);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }

        for(Order order : orderList1){
            OrderDTO orderDTO1 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO1);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> getExchangeList(){
        List<Order> orderList = orderRepository.findAllByOrderStatus(OrderStatus.EXCHANGING);
        List<Order> orderList1 = orderRepository.findAllByOrderStatus(OrderStatus.EXCHANGED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }

        for(Order order : orderList1){
            OrderDTO orderDTO1 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO1);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> List(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public void delete(Long id){
        orderRepository.deleteById(id);
    }


    @Transactional
    public void update(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.CANCELED);
        });
    }

    @Transactional
    public OrderDTO read2(Long userIdx){
        Optional<Order> orderOptional = orderRepository.findTopByUserIdxOrderByOrderRegdateDesc(userIdx);
        Order order = orderOptional.get();
        OrderDTO orderDTO = OrderDTO.builder()
                .orderIdx(order.getOrderIdx())
                .orderNum(order.getOrderNum())
                .orderStatus(order.getOrderStatus())
                .orderSeller(order.getOrderSeller())
                .orderRegdate(order.getOrderRegdate())
                .orderZipcode(order.getOrderZipcode())
                .orderAddress1(order.getOrderAddress1())
                .orderAddress2(order.getOrderAddress2())
                .orderTel1(order.getOrderTel1())
                .orderTel2(order.getOrderTel2())
                .orderRequest(order.getOrderRequest())
                .orderRevname(order.getOrderRevname())
                .userIdx(order.getUserIdx())
                .goods(Goods.builder()
                        .gdIdx(order.getGoods().getGdIdx())
                        .gdName(order.getGoods().getGdName())
                        .gdImg(order.getGoods().getGdImg())
                        .build())
                .build();
        return orderDTO;
    }

    @Transactional
    public List<OrderDTO> getExchangeReturnList(Long userIdx){
        List<Order> orderList1 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.EXCHANGING);
        List<Order> orderList2 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.EXCHANGED);
        List<Order> orderList3 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.RETURNING);
        List<Order> orderList4 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.RETURNED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList1){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .gdBrand(order.getGoods().getGdBrand())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }

        for(Order order : orderList2){
            OrderDTO orderDTO2 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .gdBrand(order.getGoods().getGdBrand())
                            .build())
                    .build();
            orderDTOList.add(orderDTO2);
        }


        for(Order order : orderList3){
            OrderDTO orderDTO3 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .gdBrand(order.getGoods().getGdBrand())
                            .build())
                    .build();
            orderDTOList.add(orderDTO3);
        }

        for(Order order : orderList4){
            OrderDTO orderDTO4 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .gdBrand(order.getGoods().getGdBrand())
                            .build())
                    .build();
            orderDTOList.add(orderDTO4);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> getCanceledList(Long userIdx){

        List<Order> orderList1 = orderRepository.findAllByUserIdxAndOrderStatus(userIdx, OrderStatus.CANCELED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList1){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .orderZipcode(order.getOrderZipcode())
                    .orderAddress1(order.getOrderAddress1())
                    .orderAddress2(order.getOrderAddress2())
                    .orderTel1(order.getOrderTel1())
                    .orderTel2(order.getOrderTel2())
                    .orderRequest(order.getOrderRequest())
                    .orderRevname(order.getOrderRevname())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .gdBrand(order.getGoods().getGdBrand())
                            .gdSaleprice(order.getGoods().getGdSaleprice())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }

    @Transactional
    public void cancel(Long orderIdx){
        Optional<Order> order = orderRepository.findById(orderIdx);

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.CANCELED);
        });
    }

}
