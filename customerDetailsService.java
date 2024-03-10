package com.firstPro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.firstPro.models.customerSignUp;
import com.firstPro.repository.customerRepository;
@Service
public class customerDetailsService implements UserDetailsService {
    @Autowired
    private customerRepository customerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        customerSignUp customerSignUp = customerRepository.findByEmail(username);
        
        if (customerSignUp == null) {
            throw new UsernameNotFoundException("User Not Found With Email " + username);
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Assuming your customerSignUp class has a method to retrieve roles/authorities
        // Replace getAuthorities() with the appropriate method if necessary
        // Also, make sure to populate the authorities list with the actual roles/authorities
        // of the user retrieved from the database
        return new org.springframework.security.core.userdetails.User(
                customerSignUp.getEmail(),
                customerSignUp.getPassword(),
                authorities
        );
    }
}

