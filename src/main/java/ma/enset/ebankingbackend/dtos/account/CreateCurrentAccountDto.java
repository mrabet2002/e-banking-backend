package ma.enset.ebankingbackend.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateCurrentAccountDto extends CreateBankAccountDto {
    private Double overdraft;
}
