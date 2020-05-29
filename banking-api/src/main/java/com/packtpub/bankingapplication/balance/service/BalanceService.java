package com.packtpub.bankingapplication.balance.service;

import com.packtpub.bankingapplication.balance.domain.Balance;
import com.packtpub.bankingapplication.balance.domain.Customer;
import com.packtpub.bankingapplication.balance.persistence.BalanceRepository;
import com.packtpub.bankingapplication.balance.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {
    @Autowired
    public BalanceService(CustomerRepository customerRepository, BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Balance> getCurrentBalance(String username) {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            return balanceRepository.findByCustomer(customer.get());
        }

        return Optional.empty();
    }

    private final CustomerRepository customerRepository;
    private final BalanceRepository balanceRepository;
}
