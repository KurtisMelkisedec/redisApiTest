package com.personalProject.shoply.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalProject.shoply.data.entities.Customer;
import com.personalProject.shoply.exceptions.EntityAlreadyExists;
import com.personalProject.shoply.exceptions.EntityNotFound;
import com.personalProject.shoply.services.ICustomerService;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final RedisCommands<String, String> redisCommands;
    private final ObjectMapper mapper=new ObjectMapper();


    @Override
    public Customer saveUser(Customer customer) throws JsonProcessingException {
        if (findCustomerByPhoneNumber(customer.getPhoneNumber())!=null)throw  new EntityAlreadyExists("Un client avec ce numéro de téléphone existr déjà", HttpStatus.CONFLICT);
        String customerJson= mapper.writeValueAsString(customer);
        redisCommands.set(String.format("Customer:%s",customer.getId()),customerJson);
        return customer;
    }

    @Override
    public List<Customer> findAll() throws JsonProcessingException {
        List<Customer> customers=new ArrayList<>();
        List<String> keys=new ArrayList<>(redisCommands.keys("Customer:*"));
        for(String key: keys){
            String json=redisCommands.get(key);
            customers.add(mapper.readValue(json,Customer.class));
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(Long id) throws JsonProcessingException {
        List<Customer>customers=findAll();
        Customer customer= customers.stream()
                .filter(c->c.getId().equals(id))
                .findFirst()
                .orElse(null);

        return customer;
    }

    @Override
    public void deleteCustomer(String phoneNumber) throws JsonProcessingException {
        Customer customer=findCustomerByPhoneNumber(phoneNumber);
        if (customer==null)throw new EntityNotFound("Aucun Client avec ce numéro de téléphone",HttpStatus.NOT_FOUND);
        redisCommands.del(String.format("Customer:%s",customer.getId()));

    }

    @Override
    public Customer updateCustomerPhoneNumber(Customer customer) throws JsonProcessingException {
        if (findCustomerById(customer.getId())==null) throw  new EntityNotFound("Ce client n'existe pas",HttpStatus.NOT_FOUND);
        if (findCustomerByPhoneNumber(customer.getPhoneNumber())!=null)throw new EntityAlreadyExists("Un client avec ce numéro de téléphone existe déjà",HttpStatus.CONFLICT);
        String customerJson= mapper.writeValueAsString(customer);
        redisCommands.set(String.format("Customer:%s",customer.getId()),customerJson);
        return customer;
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) throws JsonProcessingException {
        List<Customer>customers=findAll();
        Customer customer= customers.stream()
                .filter(c->c.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
        return customer;
    }
}
