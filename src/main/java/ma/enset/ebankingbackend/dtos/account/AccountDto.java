package ma.enset.ebankingbackend.dtos.account;

import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.enums.AccountType;

import java.time.LocalDateTime;

public class AccountDto {
    private Long id;
    private CustomerDto customer;
    private String currency;
    private Double balance;
    private LocalDateTime createdAt;
    private String status;
    private AccountType accountType;
}
