package ma.enset.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("SA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount extends BankAccount {
    private Double interestRate;
}
