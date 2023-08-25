package com.abdullahturhan.reservation.service;

import com.abdullahturhan.reservation.dto.*;
import com.abdullahturhan.reservation.entity.Customer;
import com.abdullahturhan.reservation.exception.CustomerNotFoundException;
import com.abdullahturhan.reservation.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer save(Customer newCustomer) {
        if (newCustomer.getId() == null) {
            newCustomer.setCreatedAt(LocalDateTime.now());
        }

        newCustomer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(newCustomer);
    }

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return customerRepository.findCustomerByUsername(username)
                        .orElseThrow(()->new UsernameNotFoundException("Username not found"));
            }
        };
    }



    public Customer findByUsername(String username){
        return customerRepository.findCustomerByUsername(username)
                .orElseThrow(()-> new RuntimeException("Couldn't find customer'"));
    }

    public FindOneCustomerRersponse findOneCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with that  id " + id));
        return FindOneCustomerRersponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .gender(customer.getGender())
                .age(customer.getAge())
                .createdAt(customer.getCreatedAt())
                .build();

    }

    public Optional<Customer> findCustomerById(Long id){
       Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        return Optional.of(customer);
    }
    @Transactional
    public UpdateCustomerResponse updateOneCustomerById(Long id,UpdateCustomerRequest request){
        Optional<Customer>  customer = customerRepository.findById(id);
        if (customer.isPresent()){
            Customer updatedCustomer = customer.get();
            updatedCustomer.setFirstName(request.getFirstName());
            updatedCustomer.setLastName(request.getLastName());
            customerRepository.save(updatedCustomer);
            return UpdateCustomerResponse.builder()
                    .firstName(updatedCustomer.getFirstName())
                    .lastName(updatedCustomer.getLastName())
                    .updatedAt(updatedCustomer.getUpdatedAt())
                    .email(updatedCustomer.getEmail())
                    .build();


        }
        throw new CustomerNotFoundException("Customer not found with id: " + id);
    }
    @Transactional
    public void deleteCustomerById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        customer.ifPresent(customerRepository::delete);
    }
}
