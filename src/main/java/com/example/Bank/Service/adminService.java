package com.example.Bank.Service;

import com.example.Bank.Entities.Account;
import com.example.Bank.Entities.Admin;
import com.example.Bank.Entities.Client;
import com.example.Bank.Entities.Transaction;
import com.example.Bank.Repository.accountRepository;
import com.example.Bank.Repository.adminRepository;
import com.example.Bank.Repository.clientRepository;
import com.example.Bank.Repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class adminService {
    private  clientRepository c_repo;
    private accountRepository ac_repo;
    private  transactionRepository tr_repo;
    private adminRepository a_repo;


}
