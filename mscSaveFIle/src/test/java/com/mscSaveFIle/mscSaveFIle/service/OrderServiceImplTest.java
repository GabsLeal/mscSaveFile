package com.mscSaveFIle.mscSaveFIle.service;

import static org.junit.jupiter.api.Assertions.*;

import com.mscSaveFIle.mscSaveFIle.dao.OrderEntity;
import com.mscSaveFIle.mscSaveFIle.dao.OrderRepository;
import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.mapper.OrderMapper;
import com.mscSaveFIle.mscSaveFIle.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessOrders() {
        List<Order> orders = Collections.singletonList(new Order());
        List<OrderEntity> orderEntities = Collections.singletonList(new OrderEntity());
        when(orderMapper.convertToEntity(any(Order.class))).thenReturn(new OrderEntity());
        doAnswer(invocation -> {
            orderRepository.saveAll((List<OrderEntity>) invocation.getArguments()[0]);
            return null;
        }).when(orderRepository).saveAll(orderEntities);

        orderService.processOrders(orders);

        verify(orderMapper, times(orders.size())).convertToEntity(any(Order.class));
        verify(orderRepository, times(1)).saveAll(orderEntities);
    }

    @Test
    public void testGetOrders_WithAllParameters() {
        String country = "USA";
        String date = "2023-05-11";
        Double weightLimit = 10.0;
        int page = 1;
        int pageSize = 10;
        List<OrderEntity> orderEntities = Collections.singletonList(new OrderEntity());
        List<OrderDTO> expectedOrders = Collections.singletonList(new OrderDTO());
        when(orderRepository.findByCountryAndDateAndWeightLessThanEqual(country, LocalDate.parse(date), weightLimit, PageRequest.of(page, pageSize))).thenReturn(new PageImpl<>(orderEntities));
        when(orderMapper.convertToDTO(any(OrderEntity.class))).thenReturn(new OrderDTO());

        List<OrderDTO> result = orderService.getOrders(country, date, weightLimit, page, pageSize);

        assertEquals(expectedOrders, result);
        verify(orderRepository, times(1)).findByCountryAndDateAndWeightLessThanEqual(country, LocalDate.parse(date), weightLimit, PageRequest.of(page, pageSize));
        verify(orderMapper, times(orderEntities.size())).convertToDTO(any(OrderEntity.class));
    }

    @Test
    public void testGetTotalOrdersByCountry() {
        String country = "USA";
        int expectedTotalOrders = 5;
        when(orderRepository.countByCountry(country)).thenReturn(expectedTotalOrders);

        int result = orderService.getTotalOrdersByCountry(country);

        assertEquals(expectedTotalOrders, result);
        verify(orderRepository, times(1)).countByCountry(country);
    }

    @Test
    public void testGetTotalWeightByCountry() {
        String country = "USA";
        double expectedTotalWeight = 50.0;
        when(orderRepository.calculateTotalWeightByCountry(country)).thenReturn(expectedTotalWeight);

        double result = orderService.getTotalWeightByCountry(country);

        assertEquals(expectedTotalWeight, result);
        verify(orderRepository, times(1)).calculateTotalWeightByCountry(country);
    }

}
