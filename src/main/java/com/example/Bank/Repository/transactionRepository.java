package com.example.Bank.Repository;

import com.example.Bank.Entities.Client;
import com.example.Bank.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface transactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByClient(Optional<Client> client);
}
