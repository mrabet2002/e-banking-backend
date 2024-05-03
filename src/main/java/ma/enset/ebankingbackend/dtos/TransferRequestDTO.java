package ma.enset.ebankingbackend.dtos;

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
public class TransferRequestDTO {
    private String fromAccountId;
    private String toAccountId;
    private Double amount;
    private String description;
    private Long createdBy;
}
