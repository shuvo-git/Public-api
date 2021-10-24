package com.api.padma_public.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class PhoneRequest {
    private String username;
    private String password;
    private String phoneNumber;
}
