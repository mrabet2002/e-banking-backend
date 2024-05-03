package ma.enset.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.enset.ebankingbackend.enums.OperationType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateOperationDto {
    private Double amount;
    private String description;
    private String accountId;
    private Long createdBy;
}
