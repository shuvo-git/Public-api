package com.api.padma_public.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Transactions {
    private  String date;
    private  String transactionDesc;
    private  String debit;
    private  String credit;
    private  String balance;
}
