package com.packtpub.bankingapplication.balance.persistence;

import com.packtpub.bankingapplication.balance.domain.Balance;
import com.packtpub.bankingapplication.balance.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    public abstract Optional<Balance> findByCustomer(Customer customer);
}
