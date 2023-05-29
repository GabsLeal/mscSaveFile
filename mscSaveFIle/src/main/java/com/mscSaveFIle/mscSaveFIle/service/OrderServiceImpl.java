package com.mscSaveFIle.mscSaveFIle.service;

import com.mscSaveFIle.mscSaveFIle.dao.OrderEntity;
import com.mscSaveFIle.mscSaveFIle.dao.OrderRepository;
import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.mapper.OrderMapper;
import com.mscSaveFIle.mscSaveFIle.model.Order;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void processOrders(List<Order> orders) {
        List<OrderEntity> orderEntities = orders.stream()
                .map(orderMapper::convertToEntity)
                .collect(Collectors.toList());
        orderRepository.saveAll(orderEntities);
    }

    @Override
    public List<OrderDTO> getOrders(String country, String date, Double weightLimit, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<OrderEntity> orderPage;

        if (country != null && date != null && weightLimit != null) {
            LocalDate parsedDate = LocalDate.parse(date);
            orderPage = orderRepository.findByCountryAndDateAndWeightLessThanEqual(country, parsedDate, weightLimit, pageable);
        } else if (country != null && date != null) {
            LocalDate parsedDate = LocalDate.parse(date);
            orderPage = orderRepository.findByCountryAndDate(country, parsedDate, pageable);
        } else if (country != null && weightLimit != null) {
            orderPage = orderRepository.findByCountryAndWeightLessThanEqual(country, weightLimit, pageable);
        } else if (country != null) {
            orderPage = orderRepository.findByCountry(country, pageable);
        } else {
            orderPage = orderRepository.findAll(pageable);
        }

        List<OrderDTO> orderDTOs = orderPage.map(orderMapper::convertToDTO).getContent();

        return orderDTOs;
    }

    @Override
    public int getTotalOrdersByCountry(String country) {
        if (country != null) {
            return orderRepository.countByCountry(country);
        }
        return 0;
    }

    @Override
    public double getTotalWeightByCountry(String country) {
        if (country != null) {
            Double totalWeight = orderRepository.calculateTotalWeightByCountry(country);
            return totalWeight != null ? totalWeight : 0;
        }
        return 0;
    }
}
