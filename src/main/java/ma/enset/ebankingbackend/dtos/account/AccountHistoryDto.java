package ma.enset.ebankingbackend.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.dtos.OperationDto;
import ma.enset.ebankingbackend.entities.Operation;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryDto {
    private String id;
    private Page<OperationDto> operations;
    private Double balance;
    private CustomerDto customer;
}
