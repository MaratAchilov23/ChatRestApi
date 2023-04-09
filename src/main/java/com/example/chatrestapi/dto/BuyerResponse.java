package com.example.chatrestapi.dto;

import java.util.List;

public class BuyerResponse {
    private List<BuyerDTO>buyerDTOList;

    public BuyerResponse() {
    }

    public BuyerResponse(List<BuyerDTO> buyerDTOList) {
        this.buyerDTOList = buyerDTOList;
    }

    public List<BuyerDTO> getBuyerDTOList() {
        return buyerDTOList;
    }

    public void setBuyerDTOList(List<BuyerDTO> buyerDTOList) {
        this.buyerDTOList = buyerDTOList;
    }
}
