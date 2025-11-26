package com.spring.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @NotBlank(message = "firstName is required")
    private String firstName;
    @Email(message = "Email is Invalid")
    private String email;
    @Size(min=10,max=10,message = "phoneNumber must be in 10 digits")
    private String phoneNumber;

    private String accountNumber;
    private double balance;
}
