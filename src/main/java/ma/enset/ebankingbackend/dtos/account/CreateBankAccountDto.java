package ma.enset.ebankingbackend.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.enums.AccountType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankAccountDto {
    private Double balance;
    private Long customerId;
}
