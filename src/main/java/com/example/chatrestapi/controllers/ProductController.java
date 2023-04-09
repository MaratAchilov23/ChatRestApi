package com.example.chatrestapi.controllers;

import com.example.chatrestapi.dto.ProductDTO;
import com.example.chatrestapi.dto.ProductResponse;
import com.example.chatrestapi.models.Product;
import com.example.chatrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
     private final ProductService productService;
     private final ModelMapper modelMapper;
    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ProductResponse all(){
        return new ProductResponse(productService.findAll().stream().
                map(this::converterToProductDTO).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus>addProduct(@RequestBody @Valid ProductDTO productDTO,
                                                BindingResult bindingResult) throws Exception {
        Product product = convertorToProduct(productDTO);
        if (bindingResult.hasFieldErrors()){
            StringBuilder builder = new StringBuilder();
            List<FieldError>errors = bindingResult.getFieldErrors();
            for (FieldError er:errors){
                builder.append(er.getField())
                        .append("-")
                        .append(er.getDefaultMessage())
                        .append(";");
            }
            throw new Exception(builder.toString());
        }
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/title")
    public ResponseEntity<ProductDTO> getByTitle(@RequestParam String title) {
        Optional<Product> optionalProduct = Optional.ofNullable(productService.findByTitle(title));
        if (optionalProduct.isPresent()) {
            ProductDTO productDTO = converterToProductDTO(optionalProduct.get());
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.notFound().build();
    }









    private ProductDTO converterToProductDTO(Product product){
      return   modelMapper.map(product, ProductDTO.class);
    }

    private Product convertorToProduct(ProductDTO productDTO){
      return   modelMapper.map(productDTO,Product.class);
    }
}
