package com.example.chatrestapi.controllers;

import com.example.chatrestapi.dto.BuyerDTO;
import com.example.chatrestapi.dto.OrderDTO;
import com.example.chatrestapi.dto.OrderResponse;
import com.example.chatrestapi.dto.ProductDTO;
import com.example.chatrestapi.models.Buyer;
import com.example.chatrestapi.models.Order;
import com.example.chatrestapi.models.Product;
import com.example.chatrestapi.repository.BuyerRepository;
import com.example.chatrestapi.repository.ProductRepository;
import com.example.chatrestapi.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final ModelMapper modelMapper;

    private final OrderService orderService;

    private final ProductRepository productRepository;

    private final BuyerRepository buyerRepository;
   @Autowired
    public OrderController(ModelMapper modelMapper, OrderService orderService, ProductRepository productRepository, BuyerRepository buyerRepository) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.buyerRepository = buyerRepository;
    }

    @GetMapping("/all")
    public OrderResponse getAll() {
        List<OrderDTO> orderDTOList = orderService.findAll()
                .stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());

        return new OrderDTO.Builder()
                .orderList(orderDTOList)
                .build()
                .toOrderResponse();
    }

    private OrderDTO convertToOrderDTO(Order order) {
        Buyer buyer = order.getBuyer();
        List<ProductDTO> productDTOList = order.getProducts()
                .stream()
                .map(productOptional -> productOptional.map(this::convertToProductDTO).orElseThrow(() -> new IllegalArgumentException("Product not found")))
                .collect(Collectors.toList());
        return new OrderDTO(order.getTotalOrderAmount(), new BuyerDTO(buyer.getName()), productDTOList);
    }

    private Order convertToOrder(OrderDTO orderDTO) {
        List<Product> productList = orderDTO.getProducts().stream()
                .map(productDTO -> productRepository.findByTitle(productDTO.getTitle())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found")))
                .collect(Collectors.toList());

        return new Order.Builder()
                .totalOrderAmount(orderDTO.getTotalOrderAmount())
                .buyer(buyerRepository.findByName(orderDTO.getBuyer().getName()))
                .products(productList)
                .build();
    }
    private ProductDTO convertToProductDTO(Optional<Product> productOptional) {
        Product product = productOptional.orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return new ProductDTO(product.getTitle(), product.getPrice(), product.getDescription());
    }

    private Product converterToProduct(ProductDTO productDTO){
        return   modelMapper.map(productDTO,Product.class);
    }
}
