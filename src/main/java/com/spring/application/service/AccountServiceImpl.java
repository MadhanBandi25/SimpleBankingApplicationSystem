package com.spring.application.service;

import com.spring.application.dto.AccountDto;
import com.spring.application.entity.Account;
import com.spring.application.mapper.AccountMapper;
import com.spring.application.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //  custom  mapper
          Account account = AccountMapper.mapToAccount(accountDto);
         Account savedAccount = accountRepository.save(account);
        AccountDto accountDto1 = AccountMapper.mapToAccountDto(savedAccount);

        // this one is modelmapper
//        Account account= modelMapper.map(accountDto,Account.class);
//        Account savedAccount = accountRepository.save(account);
//        AccountDto accountDto1=modelMapper.map(savedAccount,AccountDto.class);
        return accountDto1;
    }

    @Override
    public AccountDto getByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (!account.isPresent()) {
            return null;
        }
        Account account1 = account.get();
       //  this is custom sorting
        AccountDto accountDto = AccountMapper.mapToAccountDto(account1);

        //using is model mapper
       // AccountDto accountDto=modelMapper.map(account1, AccountDto.class);
        return accountDto;
    }

    @Override
    public List<AccountDto> getAllDetails() {
        List<Account> account = accountRepository.findAll();
        //using custom mapper
        List<AccountDto> accountDtos = account.stream().map(AccountMapper::mapToAccountDto).toList();
        return  accountDtos;
       // return  account.stream().map((account1)->modelMapper.map(account1, AccountDto.class)).collect(Collectors.toList());
    }

    @Override
    public AccountDto transaction(String accountNumber, String type, double amount) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);

        if (!optionalAccount.isPresent()) {
            return null; // Account not found
        }

        Account account = optionalAccount.get();
        double balance = account.getBalance();

        if ("deposit".equalsIgnoreCase(type)) {
            account.setBalance(balance + amount);
        }
        else if ("withdraw".equalsIgnoreCase(type)) {
            if (amount > balance) {
                return null; // Insufficient funds
            }
            account.setBalance(balance - amount);
        }
        else {
            return null; // Invalid transaction type
        }

        accountRepository.save(account); // Save updated account
        return AccountMapper.mapToAccountDto(account);
    }


    @Override
    public boolean deleteAccount(String accountNumber) {
 Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
 if(!account.isPresent()){
     return false;
 }
        accountRepository.deleteByAccountNumber(accountNumber);
 return  true;
    }

    @Override
    public AccountDto updatePhoneNumber(String accountNumber, String phoneNumber) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setPhoneNumber(phoneNumber);  // assuming your Account entity has phoneNumber field
            accountRepository.save(account);
            return AccountMapper.mapToAccountDto(account);  // map entity to dto
        }
        return null;
    }



}
