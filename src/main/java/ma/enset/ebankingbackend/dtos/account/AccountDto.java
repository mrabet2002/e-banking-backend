package ma.enset.ebankingbackend.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.enums.AccountType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String id;
    private CustomerDto customer;
    private String currency;
    private Double balance;
    private LocalDateTime createdAt;
    private String status;
}
