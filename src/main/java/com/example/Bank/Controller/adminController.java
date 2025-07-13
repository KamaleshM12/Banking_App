package com.example.Bank.Controller;

import com.example.Bank.Entities.Admin;
import com.example.Bank.Entities.Client;
import com.example.Bank.Entities.Transaction;
import com.example.Bank.Service.adminService;
//import com.example.Bank.Service.adminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class adminController {

    final private adminService a_service;

    // 🔹 Register a new admin (with branch reference)
    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        Admin saved = a_service.register(admin);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 🔹 Get list of all clients
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = a_service.getAllClients();
        return ResponseEntity.ok(clients);
    }

    // 🔹 Delete a client by email
    @DeleteMapping("/clients/{email}")
    public ResponseEntity<String> deleteClient(@PathVariable String email) {
        a_service.deleteClient(email);
        return ResponseEntity.ok("Client deleted successfully.");
    }

    // 🔹 View all transactions across all clients
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = a_service.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
