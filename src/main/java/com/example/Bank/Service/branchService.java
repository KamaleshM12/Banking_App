package com.example.Bank.Service;

import com.example.Bank.Entities.Branch;
import com.example.Bank.Repository.branchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class branchService {
    @Autowired
    branchRepository br_repo;

    public String addbranch(Branch branch) {
        Optional<Branch> branchOpt = br_repo.findById(branch.getId());
        if(branchOpt.isPresent()) {
            return "Branch already exists";
        }
        br_repo.save(branch);
        return "Branch added";
    }
}
