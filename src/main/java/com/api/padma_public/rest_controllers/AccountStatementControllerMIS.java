package com.api.padma_public.rest_controllers;

import com.api.padma_public.entities.AccountRequest;
import com.api.padma_public.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountStatementControllerMIS {
    //@Autowired
    //private AccountsRepoImpl accRepo;
    
    @Autowired
    private Utils utils;

    @PostMapping("getAccountStatement")
    public ResponseEntity<String> getAccountStatement(@RequestBody AccountRequest accountRequest)
    {
        String accountNumber = accountRequest.getAccountNumber();
        final String uri = "get_acc_statement?accNum="+accountNumber;
        String accountStatement = utils.apiRequest(uri);

        if(accountStatement.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountStatement);
        else return ResponseEntity.status(HttpStatus.OK).body(accountStatement);
    }
}
