package ma.enset.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.enset.ebankingbackend.enums.OperationType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private Long id;
    private double amount;
    private String description;
    private OperationType type;
    private LocalDateTime date;
    private Long doneBy;
}
