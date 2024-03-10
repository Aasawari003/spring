package com.firstPro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstPro.models.User;
import com.firstPro.models.customerSignUp;

@Repository
public interface customerRepository extends JpaRepository<customerSignUp, Integer>{

	public customerSignUp findByEmail(String email);

	public static Optional<customerSignUp> findCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
