package com.example.project.controller.api;

import com.example.project.model.DTO.AdminDTO;
import com.example.project.model.DTO.OrderDTO;
import com.example.project.service.AdminService;
import com.example.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin2")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @PostMapping("/new")
    public void create(@RequestBody AdminDTO adminDTO){
        adminService.create(adminDTO);
    }

    @GetMapping("/list")
    public List<AdminDTO> list(){
        List<AdminDTO> adminDTOList = adminService.getAdminList();
        return adminDTOList;
    }

//    @PutMapping("")
//    public void update(@RequestBody AdminDTO adminDTO){
//        adminService.update(adminDTO);
//    }

    @RestController
    @RequestMapping("/order")
    @RequiredArgsConstructor
    public static class OrdersController {

        private final OrderService orderService;

        @PostMapping("/new/{userIdx}")
        public void create(@PathVariable Long userIdx, @RequestBody OrderDTO orderDTO){
            orderService.create(userIdx, orderDTO);
        }

        @GetMapping("/list/{userIdx}")
        public List<OrderDTO> list(@PathVariable Long userIdx){
            List<OrderDTO> orderDTOList = orderService.getOrderList(userIdx);
            return orderDTOList;
        }

        @PutMapping("/ready")
        public void updateReady(@RequestBody OrderDTO orderDTO) {
            orderService.updateReady(orderDTO);
        }

        @PutMapping("/send")
        public void updateSend(@RequestBody OrderDTO orderDTO) {
            orderService.updateSend(orderDTO);
        }

        @PutMapping("/finish")
        public void updateFinish(@RequestBody OrderDTO orderDTO) {
            orderService.updateFinish(orderDTO);
        }

        @PutMapping("/cancle")
        public void updateCancle(@RequestBody OrderDTO orderDTO) {
            orderService.updateCancel(orderDTO);
        }

        @PutMapping("/returning")
        public void updateReturning(@RequestBody OrderDTO orderDTO) {
            orderService.updateReturning(orderDTO);
        }

        @PutMapping("/returned")
        public void updateReturned(@RequestBody OrderDTO orderDTO) {
            orderService.updateReturned(orderDTO);
        }

        @GetMapping("/cancellist")
        public List<OrderDTO> cancelList(){
            List<OrderDTO> orderDTOList = orderService.getCancelList();
            return orderDTOList;
        }
//        @GetMapping("/returnedlist")
//        public List<OrderDTO> returnList(){
//            List<OrderDTO> orderDTOList = orderService.getReturnList();
//            return orderDTOList;
//        }
//        @GetMapping("/returninglist")
//        public List<OrderDTO> exchangeList(){
//            List<OrderDTO> orderDTOList = orderService.getExchangeList();
//            return orderDTOList;
//        }

        @PutMapping("/update")
        public void update(@RequestBody OrderDTO orderDTO) {
            orderService.update(orderDTO);
        }
    }
}
