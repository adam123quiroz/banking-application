package com.packtpub.bankingapplication.security.service;

import com.packtpub.bankingapplication.balance.domain.Credentials;
import com.packtpub.bankingapplication.balance.domain.Customer;
import com.packtpub.bankingapplication.balance.persistence.CustomerRepository;
import com.packtpub.bankingapplication.security.domain.JwtUser;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
public class LoginService {
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;

    public LoginService(CustomerRepository customerRepository, JwtService jwtService) {
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }

    public String login(Credentials credentials) throws LoginException {
        Optional<Customer> customer = customerRepository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
        if (customer.isPresent()) {
            JwtUser user = new JwtUser();
            user.setUsername(credentials.getUsername());
            user.setRole("CUSTOMER");
            return jwtService.getToken(user);
        }

        throw new LoginException("Invalid credentials provided");
    }
}
