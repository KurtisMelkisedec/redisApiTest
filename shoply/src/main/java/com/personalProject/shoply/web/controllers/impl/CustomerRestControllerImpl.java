package com.personalProject.shoply.web.controllers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.personalProject.shoply.data.entities.Customer;
import com.personalProject.shoply.services.ICustomerService;
import com.personalProject.shoply.web.controllers.ICustomerRestController;
import com.personalProject.shoply.web.dtos.request.CustomerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestControllerImpl implements ICustomerRestController {
    private final ICustomerService customerService;
    @Override
    public ResponseEntity<?> addCustomer(CustomerRequestDto customer) throws JsonProcessingException {
        Customer newCustomer= new Customer(customer.getFullName(),customer.getPhoneNumber());
        newCustomer= customerService.saveUser(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findAllCustomer() throws JsonProcessingException {
        List<Customer>customers=customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCustomer(String phoneNumber) throws JsonProcessingException{
        customerService.deleteCustomer(phoneNumber);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> updateCustomer(Customer customer) throws JsonProcessingException {

        Customer newCustomer=customerService.updateCustomerPhoneNumber(customer);
        return new ResponseEntity<>(newCustomer,HttpStatus.OK);
    }
}
