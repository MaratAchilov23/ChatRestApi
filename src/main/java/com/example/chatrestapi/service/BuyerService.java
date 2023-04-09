package com.example.chatrestapi.service;

import com.example.chatrestapi.models.Buyer;
import com.example.chatrestapi.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BuyerService {

    private final BuyerRepository repository;
    @Autowired
    public BuyerService(BuyerRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public void saveBuyer(Buyer buyer){
        repository.save(buyer);
    }

    public List<Buyer> findAll(){
        return repository.findAll();
    }

    public Buyer findByName(String name){
        return repository.findByName(name).orElse(null);
    }

    public Buyer findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Buyer>findWithLowBalance(){
        return repository.findAllByBalanceLessThanTen();
    }


}
