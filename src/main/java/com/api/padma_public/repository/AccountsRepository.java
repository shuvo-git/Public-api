package com.api.padma_public.repository;

import java.util.List;

public interface AccountsRepository<CustomerAccount>{
    List<CustomerAccount> getAccountsByPhnNumber(String phn);
    List<Object> getAccountStmtByAccNumber(String acc);
    List<Object> getAccountInfo(String acc);
}
