package com.example.Bank.Repository;

import com.example.Bank.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountRepository extends JpaRepository<Account,String> {
}
