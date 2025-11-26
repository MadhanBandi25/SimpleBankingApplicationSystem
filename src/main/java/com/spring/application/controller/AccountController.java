package com.spring.application.controller;

import com.spring.application.dto.AccountDto;
import com.spring.application.entity.Account;
import com.spring.application.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")

public class AccountController {

     @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid  @RequestBody AccountDto accountDto){
       return ResponseEntity.ok(accountService.createAccount(accountDto));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getByAccountNumber(@PathVariable String accountNumber){
        AccountDto accountDto = accountService.getByAccountNumber(accountNumber);
        if(accountDto == null){
            return new ResponseEntity<>("Account not  found with this account number",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountService.getByAccountNumber(accountNumber),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll()
    {
        List<AccountDto> accountDtoList = accountService.getAllDetails();
        return  ResponseEntity.ok(accountDtoList);
    }

    @GetMapping("/{accountNumber}/{type}/{amount}")
    public ResponseEntity<?> transaction(@PathVariable String accountNumber, @PathVariable String type, @PathVariable double amount){

        AccountDto accountDto1 = accountService.transaction(accountNumber,type,amount);
        if(accountDto1 == null)
        {
            return new ResponseEntity<>("transaction failed ", HttpStatus.NOT_FOUND);
        }
        double current_balance = accountDto1.getBalance();
        return new ResponseEntity<>("Transaction successful.... Current Balance : " + current_balance,HttpStatus.OK);
    }

    @DeleteMapping("{accountNumber}")
    public ResponseEntity<String> delete(@PathVariable String accountNumber){
         boolean bool = accountService.deleteAccount(accountNumber);
        if(!bool){
            return new ResponseEntity<>("account not found",HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>("delete account successfully",HttpStatus.OK);

    }

    @PutMapping("/{accountNumber}/updatePhone")
    public ResponseEntity<?> updatePhone(@PathVariable String accountNumber,
                                         @RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");

        AccountDto accountDto = accountService.updatePhoneNumber(accountNumber, phoneNumber);

        if (accountDto == null) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }




}
