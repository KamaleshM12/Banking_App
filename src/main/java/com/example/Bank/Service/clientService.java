package com.example.Bank.Service;

import com.example.Bank.Entities.Account;
import com.example.Bank.Entities.Client;
import com.example.Bank.Entities.Transaction;
import com.example.Bank.Repository.accountRepository;
import com.example.Bank.Repository.clientRepository;
import com.example.Bank.Repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class clientService {
    @Autowired
    private  clientRepository c_repo;

    @Autowired
    private accountRepository ac_repo;

    @Autowired
    private  transactionRepository tr_repo;

//    public static Client getClientWithEmail(String email) {
//        Optional<Client> c = c_repo.findById(email);
//        if (c.isPresent()) {
//            return c.get();
//        }
//        new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
//        return null;
//    }


    public Client registerClient(Client client) {
        if (c_repo.existsById(client.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client already registered");        }
        Account account = client.getAccount();
        account.setClient(client);
        client.setRole("client");
        return c_repo.save(client);
    }

    public  ResponseEntity<Account> getClient(String email) {
        Optional<Client> c = c_repo.findById(email);
        if (c.isPresent()) {
            Account account = c.get().getAccount();
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public  List<Transaction> getTransactions(String email) {
//            Client client = getClientWithEmail(email);
//            return tr_repo.findByClient(client);
        Optional<Client> c = c_repo.findById(email);
        if (c.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User not found");

        }
        return tr_repo.findByClient(c);
    }

    public void deposit(String email, Double amount) {
        Optional<Client> c = c_repo.findById(email);
        if (c.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User not found");
        }

        Client client = c.get();
        Account account = client.getAccount();
        account.setBalance(account.getBalance() + amount);
        ac_repo.save(account);

        Transaction t = new Transaction();
        t.setClient(client);
        t.setType("Deposit");
        t.setAmount(amount);
        t.setDate(LocalDateTime.now());
        tr_repo.save(t);
    }

    public void withdraw(String email, Double w_amount) {
        Optional<Client> c = c_repo.findById(email);
        if (c.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User not found");
        }
        Client client = c.get();
        Account account = client.getAccount();
        if(account.getBalance() < w_amount) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient balance");
        }
        account.setBalance(account.getBalance() - w_amount);
        ac_repo.save(account);

        Transaction t = new Transaction();
        t.setClient(client);
        t.setType("Withdraw");
        t.setAmount(w_amount);
        t.setDate(LocalDateTime.now());
        tr_repo.save(t);

    }

    public void transfer(String from, String to, Double t_amount) {
        Optional<Client> from_c = c_repo.findById(from);
        Optional<Client> to_c = c_repo.findById(to);

        if (from_c.isEmpty() || to_c.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Client fromclient = from_c.get();
        Client toclient = to_c.get();

        Account from_acc = fromclient.getAccount();
        Account to_acc = toclient.getAccount();

        if (from_acc.getBalance() < t_amount) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient balance");
        }

        // Perform transfer
        from_acc.setBalance(from_acc.getBalance() - t_amount);
        to_acc.setBalance(to_acc.getBalance() + t_amount);

        ac_repo.save(from_acc);
        ac_repo.save(to_acc);

        // Log sender transaction
        Transaction f_t = new Transaction();
        f_t.setClient(fromclient);
        f_t.setType("Transfer Sent to " + to);
        f_t.setAmount(t_amount);
        f_t.setDate(LocalDateTime.now());
        tr_repo.save(f_t);

        // Log receiver transaction
        Transaction t_t = new Transaction();
        t_t.setClient(toclient);
        t_t.setType("Transfer Received from " + from);
        t_t.setAmount(t_amount);
        t_t.setDate(LocalDateTime.now());
        tr_repo.save(t_t);
    }

}
