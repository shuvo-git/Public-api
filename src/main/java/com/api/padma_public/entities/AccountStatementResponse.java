package com.api.padma_public.entities;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AccountStatementResponse
{
    private String openingBalance;
    private ArrayList<Transactions> transactions;
    private String closingBalance;
}
