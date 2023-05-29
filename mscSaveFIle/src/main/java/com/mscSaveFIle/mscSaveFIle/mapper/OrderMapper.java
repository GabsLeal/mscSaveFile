package com.mscSaveFIle.mscSaveFIle.mapper;

import com.mscSaveFIle.mscSaveFIle.dao.OrderEntity;
import com.mscSaveFIle.mscSaveFIle.dto.OrderDTO;
import com.mscSaveFIle.mscSaveFIle.model.Order;

public class OrderMapper {
    public OrderEntity convertToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPhoneNumber(order.getPhoneNumber());
        orderEntity.setCountry(order.getCountry());
        orderEntity.setCustomerName(order.getCustomerName());
        return orderEntity;
    }

    public OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPhoneNumber(order.getPhoneNumber());
        orderDTO.setCountry(order.getCountry());
        orderDTO.setCustomerName(order.getCustomerName());
        return orderDTO;
    }
    public Order convertToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setCountry(orderDTO.getCountry());
        order.setCustomerName(orderDTO.getCustomerName());
        return order;
    }
    public OrderDTO convertToDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPhoneNumber(orderEntity.getPhoneNumber());
        orderDTO.setCountry(orderEntity.getCountry());
        orderDTO.setCustomerName(orderEntity.getCustomerName());
        return orderDTO;
    }
}
