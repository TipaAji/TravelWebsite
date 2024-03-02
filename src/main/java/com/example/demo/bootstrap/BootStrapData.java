package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Division division = divisionRepository.getById(04L);
        if(customerRepository.count() == 1) {
            Customer cus1 = new Customer("Bill", "Roberts", "111 H St", "1234", "123", division);
            Customer cus2 = new Customer("Will", "Roberts", "111 H St", "1234", "123", division);
            Customer cus3 = new Customer("Phil", "Roberts", "111 H St", "1234", "123", division);
            Customer cus4 = new Customer("Jill", "Roberts", "111 H St", "1234", "123", division);
            Customer cus5 = new Customer("Gill", "Roberts", "111 H St", "1234", "123", division);

            customerRepository.save(cus1);
            customerRepository.save(cus2);
            customerRepository.save(cus3);
            customerRepository.save(cus4);
            customerRepository.save(cus5);
        }

    }
}
