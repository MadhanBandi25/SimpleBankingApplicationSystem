package com.spring.application.mapper;

import com.spring.application.dto.AccountDto;
import com.spring.application.entity.Account;

import java.util.Random;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        // dto - > entity
        Account account = new Account();
        account.setFirstName(accountDto.getFirstName());
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setEmail(accountDto.getEmail());
        account.setBalance(0);
        account.setAccountNumber(generateAccountNumber());
        return account;
    }
    public static  AccountDto mapToAccountDto(Account account){
        AccountDto accountDto1 = new AccountDto();
        accountDto1.setFirstName(account.getFirstName());
        accountDto1.setEmail(account.getEmail());
        accountDto1.setPhoneNumber(account.getPhoneNumber());
        accountDto1.setAccountNumber(account.getAccountNumber());
        accountDto1.setBalance(account.getBalance());
        return accountDto1;
    }
    public static String generateAccountNumber() {
        Random random = new Random();

        // Generate the first digit (1 to 9 to avoid leading 0)
        int firstDigit = 1 + random.nextInt(9);

        // Generate the remaining 10 digits (0 to 9)
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(firstDigit);
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();//jkhkllk
    }
}
