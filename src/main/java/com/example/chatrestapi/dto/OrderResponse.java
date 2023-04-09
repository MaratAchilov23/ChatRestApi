package com.example.chatrestapi.dto;

import java.util.List;

public class OrderResponse {
    private final List<OrderDTO> orderList;

    public OrderResponse(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

}

