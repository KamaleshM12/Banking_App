package com.example.Bank.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Admin extends User {
    private String name;
    @ManyToOne
    @JoinColumn(name = "branchname_id")
    private Account branchname;
}
