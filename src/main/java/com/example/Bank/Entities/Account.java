package com.example.Bank.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accType;
    private Double balance;
    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Client client;
}
