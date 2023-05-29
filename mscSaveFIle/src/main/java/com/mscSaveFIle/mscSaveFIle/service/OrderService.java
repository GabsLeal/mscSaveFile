package com.mscSaveFIle.mscSaveFIle.service;

import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.model.Order;

import java.util.List;

public interface OrderService {
    void processOrders(List<Order> orders);
    List<OrderDTO> getOrders(String country, String date, Double weightLimit, int page, int pageSize);
    int getTotalOrdersByCountry(String country);
    double getTotalWeightByCountry(String country);
}
