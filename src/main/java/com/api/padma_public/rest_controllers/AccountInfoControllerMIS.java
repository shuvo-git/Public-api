package com.api.padma_public.rest_controllers;

import com.api.padma_public.entities.*;
import com.api.padma_public.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AccountInfoControllerMIS {

    //@Autowired
    //private AccountsRepoImpl accRepo;

    @Autowired
    private Utils utils;

    @PostMapping("getAllAccounts")
    public ResponseEntity<String> getAllAccounts(@RequestBody PhoneRequest phoneRequest)
    {
        String phn = phoneRequest.getPhoneNumber();
        final String uri = "get_acc_by_phn?phnNum="+phn;
        String accounts = utils.apiRequest(uri);

        if(accounts.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accounts);
        else return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @PostMapping("getSingleAccountInfoMIS")
    public ResponseEntity<String> getSingleAccountInfoMIS(
            @RequestBody AccountRequest accountRequest)
    {

        String accountNumber = accountRequest.getAccountNumber();
        final String uri = "get_acc_info?accNum="+accountNumber;
        String accountInfo = utils.apiRequest(uri);

        if(accountInfo.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountInfo);
        else return ResponseEntity.status(HttpStatus.OK).body(accountInfo);

    }


}
