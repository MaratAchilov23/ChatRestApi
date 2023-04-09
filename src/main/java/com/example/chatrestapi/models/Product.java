package com.example.chatrestapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty
    private String title;
    @Column(name = "price")
    @Min(value = 1, message = "товар не может стоит меньше одного рубля")
    @Max(value = 200000,message = "товар не может стоит больше тысячи")
    private Double price;
    @Column(name = "description")
    @Size(min = 10,max = 100, message = "описание должно быть в диапазоне от 10 до 100 символов ")
    private String description;


    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}