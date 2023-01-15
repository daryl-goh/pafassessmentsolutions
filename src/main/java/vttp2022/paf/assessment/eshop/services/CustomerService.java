package vttp2022.paf.assessment.eshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository custRepo;

    public Optional<Customer> findCustomerByName(String customerName) {
        return custRepo.findCustomerByName(customerName);
    }

    
}
