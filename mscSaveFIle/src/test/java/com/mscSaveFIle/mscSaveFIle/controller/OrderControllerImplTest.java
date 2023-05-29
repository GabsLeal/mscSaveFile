package com.mscSaveFIle.mscSaveFIle.controller;

import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.model.Order;
import com.mscSaveFIle.mscSaveFIle.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerImplTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderControllerImpl orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessOrders_WithValidOrders_ReturnsOkResponse() {
        List<Order> orders = Collections.singletonList(new Order());
        doNothing().when(orderService).processOrders(orders);

        ResponseEntity<Void> response = orderController.processOrders(orders);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(orderService, times(1)).processOrders(orders);
    }

    @Test
    public void testProcessOrders_WithException_ReturnsInternalServerErrorResponse() {
        List<Order> orders = Collections.singletonList(new Order());
        doThrow(new RuntimeException()).when(orderService).processOrders(orders);

        ResponseEntity<Void> response = orderController.processOrders(orders);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(orderService, times(1)).processOrders(orders);
    }

    @Test
    public void testGetOrders_WithValidParameters_ReturnsOkResponse() {
        String country = "USA";
        String date = "2023-05-11";
        Double weightLimit = 10.0;
        int page = 1;
        int pageSize = 10;
        List<OrderDTO> expectedOrders = Collections.singletonList(new OrderDTO());
        when(orderService.getOrders(country, date, weightLimit, page, pageSize)).thenReturn(expectedOrders);

        ResponseEntity<List<OrderDTO>> response = orderController.getOrders(country, date, weightLimit, page, pageSize);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());
        verify(orderService, times(1)).getOrders(country, date, weightLimit, page, pageSize);
    }

    @Test
    public void testGetOrders_WithException_ReturnsInternalServerErrorResponse() {
        String country = "USA";
        String date = "2023-05-11";
        Double weightLimit = 10.0;
        int page = 1;
        int pageSize = 10;
        when(orderService.getOrders(country, date, weightLimit, page, pageSize)).thenThrow(new RuntimeException());

        ResponseEntity<List<OrderDTO>> response = orderController.getOrders(country, date, weightLimit, page, pageSize);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(orderService, times(1)).getOrders(country, date, weightLimit, page, pageSize);
    }

    @Test
    public void testGetTotalOrdersByCountry_WithValidCountry_ReturnsOkResponse() {
        String country = "USA";
        int totalOrders = 100;
        when(orderService.getTotalOrdersByCountry(country)).thenReturn(totalOrders);

        ResponseEntity<Integer> response = orderController.getTotalOrdersByCountry(country);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalOrders, response.getBody());
        verify(orderService, times(1)).getTotalOrdersByCountry(country);
    }

    @Test
    public void testGetTotalOrdersByCountry_WithException_ReturnsInternalServerErrorResponse() {
        String country = "USA";
        when(orderService.getTotalOrdersByCountry(country)).thenThrow(new RuntimeException());

        ResponseEntity<Integer> response = orderController.getTotalOrdersByCountry(country);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(orderService, times(1)).getTotalOrdersByCountry(country);
    }

    @Test
    public void testGetTotalWeightByCountry_WithValidCountry_ReturnsOkResponse() {
        String country = "USA";
        double totalWeight = 500.0;
        when(orderService.getTotalWeightByCountry(country)).thenReturn(totalWeight);

        ResponseEntity<Double> response = orderController.getTotalWeightByCountry(country);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalWeight, response.getBody());
        verify(orderService, times(1)).getTotalWeightByCountry(country);
    }

    @Test
    public void testGetTotalWeightByCountry_WithException_ReturnsInternalServerErrorResponse() {
        String country = "USA";
        when(orderService.getTotalWeightByCountry(country)).thenThrow(new RuntimeException());

        ResponseEntity<Double> response = orderController.getTotalWeightByCountry(country);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(orderService, times(1)).getTotalWeightByCountry(country);
    }
}
