package com.personalProject.shoply.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.personalProject.shoply.data.entities.Customer;

import java.util.List;

public interface ICustomerService {

    public Customer saveUser(Customer customer) throws JsonProcessingException;

    public List<Customer> findAll() throws JsonProcessingException;

    public Customer findCustomerById(Long id) throws JsonProcessingException;

    public void deleteCustomer(String phoneNumber) throws JsonProcessingException;

    public Customer updateCustomerPhoneNumber(Customer customer) throws JsonProcessingException;

    public Customer findCustomerByPhoneNumber(String phoneNumber) throws JsonProcessingException;
}