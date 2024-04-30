package ma.enset.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("CA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccount extends BankAccount {
    private Double overdraft;
}
