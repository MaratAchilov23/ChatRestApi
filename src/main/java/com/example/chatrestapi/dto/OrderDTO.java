package com.example.chatrestapi.dto;

import com.example.chatrestapi.models.Buyer;
import com.example.chatrestapi.models.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDTO {
    @NotNull
    @Min(value = 1)
    private Double totalOrderAmount;
    @NotNull
    private BuyerDTO buyer;
    private List<ProductDTO> products;

    public OrderDTO(Double totalOrderAmount, BuyerDTO buyer, List<ProductDTO> products) {
        this.totalOrderAmount = totalOrderAmount;
        this.buyer = buyer;
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public Double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(Double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public BuyerDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerDTO buyer) {
        this.buyer = buyer;
    }

    public static class Builder {
        private List<OrderDTO> orderList;

        public Builder orderList(List<OrderDTO> orderList) {
            this.orderList = orderList;
            return this;
        }

        public OrderResponse build() {
            return new OrderResponse(orderList);
        }
    }
}
