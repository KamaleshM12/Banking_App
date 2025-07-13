package com.example.Bank.Repository;

import com.example.Bank.Entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface branchRepository extends JpaRepository<Branch, Long> {
}
