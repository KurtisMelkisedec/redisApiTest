package com.personalProject.shoply.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.personalProject.shoply.data.entities.Customer;
import com.personalProject.shoply.web.dtos.request.CustomerRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customers")
public interface ICustomerRestController {

    @PostMapping()
    ResponseEntity<?> addCustomer(@RequestBody CustomerRequestDto customer)throws JsonProcessingException;

    @GetMapping()
    ResponseEntity<?>findAllCustomer() throws JsonProcessingException;

    @DeleteMapping("/by-phone/{phoneNumber}")
    ResponseEntity<?>deleteCustomer(@PathVariable String phoneNumber)throws JsonProcessingException;

    @PutMapping("/by-phone")
    ResponseEntity<?>updateCustomer(@RequestBody Customer customer)throws JsonProcessingException;



}
