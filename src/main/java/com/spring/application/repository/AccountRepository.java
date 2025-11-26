package com.spring.application.repository;

import com.spring.application.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {
   Optional<Account> findByAccountNumber(String accountNumber);
   @Modifying
   @Transactional
   @Query("DELETE FROM Account a WHERE a.accountNumber = :accountNumber")
   void deleteByAccountNumber(String accountNumber);
}
