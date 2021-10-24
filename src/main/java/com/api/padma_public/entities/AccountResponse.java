package com.api.padma_public.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccountResponse {
    private String accountHolderName;
    private String accountHolderId;
    private String branchCode;
    private String branchName;
    private String accountOpeningDate;
    private String accountStatus;
    private String accountBalance;
    private String currency;
}
