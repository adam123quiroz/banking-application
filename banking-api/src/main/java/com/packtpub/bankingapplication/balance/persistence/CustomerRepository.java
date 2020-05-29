package com.packtpub.bankingapplication.balance.persistence;

import com.packtpub.bankingapplication.balance.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public abstract Optional<Customer> findByUsername(String username);

    public abstract Optional<Customer> findByUsernameAndPassword(String username, String password);
}
