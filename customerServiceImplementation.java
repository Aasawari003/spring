package com.firstPro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstPro.exceptions.userException;
import com.firstPro.models.User;
import com.firstPro.models.customerSignUp;
import com.firstPro.repository.customerRepository;

@Service
public class customerServiceImplementation implements customerService {

    @Autowired
    private customerRepository cRepository;

    @Override
    public customerSignUp signUp(customerSignUp cUp) {
        // Save the received customerSignUp object to the database
        return cRepository.save(cUp);
    }
    
    
    @Override
	public customerSignUp findCustomerById(Integer customerId) throws Exception {

		Optional<customerSignUp> customer = customerRepository.findCustomerById(customerId);
		if(customer.isPresent()) {
			return customer.get();
		}
		throw new userException("customer does not exist with id"+customerId);
	}

	@Override
	public customerSignUp findCustomerByEmail(String email) {
		customerSignUp cUp = cRepository.findByEmail(email);
		return cUp;
	}


	@Override
	public customerSignUp updateCustomer(com.firstPro.models.customerSignUp cUp, Integer customerId) throws Exception {
Optional<customerSignUp> customer1=customerRepository.findCustomerById(customerId);
		
		if (customer1.isEmpty()) {
			throw new userException("user does not exists with id"+customerId);
			
		}
	customerSignUp oldCustomer=customer1.get();
	if (cUp.getUsername()!=null) {
		oldCustomer.setUsername(cUp.getUsername());
	}
	if (cUp.getEmail()!=null) {
		oldCustomer.setEmail(cUp.getEmail());
	}
	
	customerSignUp updatedcustomer=cRepository.save(oldCustomer);
	return updatedcustomer;
	}
}
