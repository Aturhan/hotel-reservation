package com.abdullahturhan.reservation.repository;

import com.abdullahturhan.reservation.entity.Customer;
import com.abdullahturhan.reservation.enumTypes.Gender;
import com.abdullahturhan.reservation.enumTypes.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles(value = "integration")
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void itShouldFinCustomer_WhenUsernameExist(){

        String username = "username";

        Customer expected = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .role(Role.USER)
                .gender(Gender.MALE)
                .username(username)
                .password("password")
                .age(90)
                .email("email@example.com")
                .build();

        customerRepository.save(expected);

        Optional<Customer> actual = customerRepository.findCustomerByUsername(username);
        assertEquals(expected, actual.get());
        assertEquals(expected.getUsername(),actual.get().getUsername());
    }


}