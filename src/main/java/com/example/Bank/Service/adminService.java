package com.example.Bank.Service;

import com.example.Bank.Entities.*;
import com.example.Bank.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class adminService {
    final private  clientRepository c_repo;
    final private accountRepository ac_repo;
    final private  transactionRepository tr_repo;
    final private adminRepository a_repo;
    final private branchRepository b_repo;



    public Admin register(Admin admin) {
        Optional<Admin> a = a_repo.findById(admin.getEmail());
        if (a.isPresent()) {
//            throw  new RuntimeException("Email already registered: " + admin.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mail already exists");
        }
        if(admin.getBranch() == null || admin.getBranch().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch cannot be null");
        }
        Branch b = b_repo.findById(admin.getBranch().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Branch not found"));
        admin.setBranch(b);
        admin.setRole("Admin");
        return a_repo.save(admin);
    }


    public List<Client> getAllClients() {
        return c_repo.findAll();
    }

    public void deleteClient(String email) {
        Optional<Client> c = c_repo.findById(email);
        if (c.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
        c_repo.delete(c.get());
        ac_repo.delete(c.get().getAccount());
        List<Transaction> transaction = tr_repo.findByClient(c);
        tr_repo.deleteAll(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return tr_repo.findAll();
    }
}
