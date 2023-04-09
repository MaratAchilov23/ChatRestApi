package com.example.chatrestapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BuyerDTO {
    @Size(min = 3,max = 30,message = "Имя должно быть в диапазоне от 3х до 30ти символов")
    private String name;

    @Min(value = 0, message = "Баланс не может быть отрицательным")
    @Max(value = 1000000)
    private Double balance;

    public BuyerDTO() {
    }

    public BuyerDTO(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
