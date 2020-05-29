package com.packtpub.bankingapplication.balance;

import com.packtpub.bankingapplication.balance.domain.Balance;
import com.packtpub.bankingapplication.balance.domain.Customer;
import com.packtpub.bankingapplication.balance.persistence.BalanceRepository;
import com.packtpub.bankingapplication.balance.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class SampleData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final BalanceRepository balanceRepository;

    @Autowired
    public SampleData(CustomerRepository customerRepository, BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setUsername("rene");
        customer.setPassword("rene");
        Customer rene = customerRepository.save(customer);
        Customer customer1 = new Customer();
        customer1.setUsername("john");
        customer1.setPassword("john");
        Customer john = customerRepository.save(customer1);
        Balance balance = new Balance();
        balance.setCustomer(rene);
        balance.setBalance(999);
        balanceRepository.save(balance);
        Balance balance1 = new Balance();
        balance1.setCustomer(john);
        balance1.setBalance(777);
        balanceRepository.save(balance1);
    }
}
