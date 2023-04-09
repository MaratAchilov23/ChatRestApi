package com.example.chatrestapi.service;

import com.example.chatrestapi.models.Product;
import com.example.chatrestapi.repository.OrderRepository;
import com.example.chatrestapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }
    public List<Product>findAll(){
        return productRepository.findAll();
    }
    public Product findByTitle(String title){
        return  productRepository.findByTitle(title).orElse(null);
    }

    public Product findByPrice(double price){
        return productRepository.findByPrice(price).orElse(null);
    }

    public List<Product>findByPriceBetween(double firstPrice, double secondPrice){
        return productRepository.findByPriceBetween(firstPrice,secondPrice).orElse(null);
    }

   public Product findByDescription(String description){
        return productRepository.findByDescription(description);
   }

  @Transactional
  public void saveProduct(Product product){
        productRepository.save(product);
  }

}
