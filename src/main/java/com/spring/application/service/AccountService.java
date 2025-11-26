package com.spring.application.service;

import com.spring.application.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getByAccountNumber(String accountNumber);

    List<AccountDto> getAllDetails();

    AccountDto transaction(String accountNumber,String type,double amount);

    boolean deleteAccount(String accountNumber);

    AccountDto updatePhoneNumber(String accountNumber, String phoneNumber);
}
