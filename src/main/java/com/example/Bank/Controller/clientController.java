package com.example.Bank.Controller;

import com.example.Bank.Entities.Account;
import com.example.Bank.Entities.Client;
import com.example.Bank.Entities.Transaction;
import com.example.Bank.Service.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class clientController {
    @Autowired
    clientService c_service;

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        Client savedc = c_service.registerClient(client);
        URI location = URI.create("/api/client/" + savedc.getEmail());
        return ResponseEntity.created(location).body(savedc);
    }


    @GetMapping("/{email}")
    public ResponseEntity<Account> getClientByEmail(@PathVariable String email) {
        return c_service.getClient(email);
    }

    @GetMapping("/{email}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String email) {
        List<Transaction> transactions = c_service.getTransactions(email);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String email,
                                          @RequestParam Double amount) {
        c_service.deposit(email, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String email,
                                           @RequestParam Double w_amount){
        c_service.withdraw(email,w_amount);
        return ResponseEntity.ok(w_amount+"Amount Withdrwed successful");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double amount) {
        c_service.transfer(from, to, amount);
        return ResponseEntity.ok("Transfer successful");
    }


}
