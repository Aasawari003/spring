package com.firstPro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.firstPro.config.JwtProvider;
import com.firstPro.exceptions.userException;
import com.firstPro.models.User;
import com.firstPro.models.customerSignUp;
import com.firstPro.repository.customerRepository;
import com.firstPro.response.AuthResponse;
import com.firstPro.service.customerService;
@Controller
public class customerController {

    @Autowired
    private customerService customerService;

    // Other autowired dependencies and methods...

    @GetMapping("/customersignup")
    public String processSignupForm(Model model) {
        // Add any necessary model attributes
        return "customersignup"; // Assuming "customersignup" is the name of your Thymeleaf template
    }


    @PostMapping("/customersignup")
    public String signincustomerAccount(@ModelAttribute("customer") customerSignUp customerSignUp, RedirectAttributes redirectAttributes) {
        customerService.signUp(customerSignUp);
        redirectAttributes.addFlashAttribute("successMessage", "Your account has been successfully created!");
        return "redirect:/customersignup";
    }
}
