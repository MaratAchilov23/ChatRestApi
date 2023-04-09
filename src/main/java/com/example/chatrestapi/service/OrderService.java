package com.example.chatrestapi.service;

import com.example.chatrestapi.models.Buyer;
import com.example.chatrestapi.models.Order;
import com.example.chatrestapi.models.Product;
import com.example.chatrestapi.repository.BuyerRepository;
import com.example.chatrestapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, BuyerRepository buyerRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
    }

    public Order findByBuyer(String buyerName){
        Optional<Buyer> buyer = buyerRepository.findByName(buyerName);
        if(buyer==null){
            throw new NoSuchElementException("В базе нет покупателя с таким именем");
        }
        return orderRepository.findByBuyer(buyer.get().getName()).orElse(null);
    }

    public Order findByOrderAmount(double totalAmount){
       return orderRepository.findByTotalOrderAmount(totalAmount).get();
    }

    public List<Order>findBetweenDate(LocalDateTime startDate, LocalDateTime endDate){
        return orderRepository.findByTimestampBetween(startDate,endDate);
    }

    public List<Order>findByPaidFor(boolean paidFor){
        return orderRepository.findByPaidFor(paidFor);
    }

    public List<Order>findByProducts(Product product){
        return orderRepository.findByProducts(product);
    }

    @Transactional
    public void createOrder(Order order){
        enrichOrder(order);
        orderRepository.save(order);
    }

    private static void enrichOrder(Order order){
        order.setPaidFor(false);
        order.setTimestamp(LocalDateTime.now());
    }

    public List<Order>findAll(){
        return orderRepository.findAll();
    }


}
