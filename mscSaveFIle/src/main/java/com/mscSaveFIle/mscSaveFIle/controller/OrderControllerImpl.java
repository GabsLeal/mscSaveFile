package com.mscSaveFIle.mscSaveFIle.controller;

import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.model.Order;
import com.mscSaveFIle.mscSaveFIle.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @PostMapping
    @Override
    public ResponseEntity<Void> processOrders(@RequestBody List<Order> orders) {
        try {
            orderService.processOrders(orders);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    @Override
    public ResponseEntity<List<OrderDTO>> getOrders(String country, String date, Double weightLimit, int page, int pageSize) {
        try {
            List<OrderDTO> filteredOrders = orderService.getOrders(country, date, weightLimit, page, pageSize);
            return ResponseEntity.ok(filteredOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/country/{country}")
    @Override
    public ResponseEntity<Integer> getTotalOrdersByCountry(@PathVariable("country") String country) {
        try {
            int totalOrders = orderService.getTotalOrdersByCountry(country);
            return ResponseEntity.ok(totalOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/country/{country}/weight")
    @Override
    public ResponseEntity<Double> getTotalWeightByCountry(@PathVariable("country") String country) {
        try {
            double totalWeight = orderService.getTotalWeightByCountry(country);
            return ResponseEntity.ok(totalWeight);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
