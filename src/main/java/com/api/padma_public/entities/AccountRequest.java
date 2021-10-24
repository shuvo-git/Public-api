package com.api.padma_public.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AccountRequest {
    private String user;
    private String password;
    private String accountNumber;
}
