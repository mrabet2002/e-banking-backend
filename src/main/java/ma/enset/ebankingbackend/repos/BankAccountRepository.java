package ma.enset.ebankingbackend.repos;

import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String>{
    @Query("SELECT b FROM BankAccount b WHERE b.currency LIKE %:keyword% OR b.customer.name LIKE %:keyword%")
    <T> Page<T> search(String keyword, PageRequest of);
}
