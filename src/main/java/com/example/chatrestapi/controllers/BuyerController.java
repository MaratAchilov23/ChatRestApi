package com.example.chatrestapi.controllers;

import com.example.chatrestapi.dto.BuyerDTO;
import com.example.chatrestapi.dto.BuyerResponse;
import com.example.chatrestapi.models.Buyer;
import com.example.chatrestapi.service.BuyerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    private final ModelMapper modelMapper;
    private final BuyerService buyerService;
    @Autowired
    public BuyerController(ModelMapper modelMapper, BuyerService buyerService) {
        this.modelMapper = modelMapper;
        this.buyerService = buyerService;
    }

    @GetMapping("/all")
    public BuyerResponse all(){
        return new BuyerResponse(buyerService.findAll().stream().
                map(this::convertToBuyerDTO).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus>addBuyer(@RequestBody @Valid BuyerDTO buyerDTO,
                                              BindingResult bindingResult) throws Exception {
        Buyer buyer= convertToBuyer(buyerDTO);
        if(bindingResult.hasErrors()){
            StringBuilder builder = new StringBuilder();
            List<FieldError>errors = bindingResult.getFieldErrors();
            for(FieldError er:errors){
                builder.append(er.getField())
                        .append("-")
                        .append(er.getDefaultMessage())
                        .append(";");
            }
            throw new Exception(builder.toString());
        }
     buyerService.saveBuyer(buyer);
     return new ResponseEntity<>(HttpStatus.OK);
    }








    private Buyer convertToBuyer(BuyerDTO buyerDTO){
        return modelMapper.map(buyerDTO, Buyer.class);
    }

    private BuyerDTO convertToBuyerDTO(Buyer buyer){
        return modelMapper.map(buyer,BuyerDTO.class);
    }
}
