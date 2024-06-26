package ma.enset.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.enset.ebankingbackend.enums.OperationType;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOperationDto {
    private Double amount;
    private String description;
    private String accountId;
    private Long createdBy;
}
