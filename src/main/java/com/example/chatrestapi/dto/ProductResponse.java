package com.example.chatrestapi.dto;

import java.util.List;

public class ProductResponse {


    private List<ProductDTO> productDTOList;

    public ProductResponse() {
    }

    public ProductResponse(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }
}
