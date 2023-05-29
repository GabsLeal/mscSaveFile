package com.mscSaveFIle.mscSaveFIle.controller;

import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orders")
public interface OrderController {

    @PostMapping
    ResponseEntity<Void> processOrders(@RequestBody List<Order> orders);

    @GetMapping
    ResponseEntity<List<OrderDTO>> getOrders(
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "weightLimit", required = false) Double weightLimit,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    );

    @GetMapping("/country/{country}")
    ResponseEntity<Integer> getTotalOrdersByCountry(@PathVariable("country") String country);

    @GetMapping("/country/{country}/weight")
    ResponseEntity<Double> getTotalWeightByCountry(@PathVariable("country") String country);
}
