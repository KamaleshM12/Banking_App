package com.example.Bank.Controller;

import com.example.Bank.Entities.Branch;
import com.example.Bank.Service.branchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/branch")

public class branchController {
    @Autowired
    branchService b_service;

    @PostMapping("/addbranch")
    public String addbranch(@RequestBody Branch branch) {
        return b_service.addbranch(branch);
    }

}
