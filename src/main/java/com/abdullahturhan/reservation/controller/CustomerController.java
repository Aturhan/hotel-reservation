package com.abdullahturhan.reservation.controller;

import com.abdullahturhan.reservation.dto.*;
import com.abdullahturhan.reservation.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<FindOneCustomerRersponse> findOne(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.FOUND)
                .body(customerService.findOneCustomer(id));
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateCustomerResponse> updateOne(@PathVariable Long id, @RequestBody UpdateCustomerRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateOneCustomerById(id,request));
    }
    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable Long id){
        customerService.deleteCustomerById(id);

    }
}
