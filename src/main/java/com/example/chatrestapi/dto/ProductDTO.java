package com.example.chatrestapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    @NotEmpty
    private String title;

    @Min(value = 1, message = "товар не может стоит меньше одного рубля")
    @Max(value = 200000,message = "товар не может стоит больше тысячи")
    private Double price;

    @Size(min = 10,max = 100, message = "описание должно быть в диапазоне от 10 до 100 символов ")
    private String description;

    public ProductDTO(String title, Double price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
