package com.firstPro.service;

import com.firstPro.exceptions.userException;
import com.firstPro.models.User;
import com.firstPro.models.customerSignUp;



public interface customerService {

	customerSignUp findCustomerById(Integer customerId) throws Exception;

	customerSignUp signUp(customerSignUp cUp);

	customerSignUp findCustomerByEmail(String email);

	customerSignUp updateCustomer(customerSignUp cUp, Integer customerId) throws Exception;
	}
