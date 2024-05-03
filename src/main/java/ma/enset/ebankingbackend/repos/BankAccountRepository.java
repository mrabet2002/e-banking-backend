package ma.enset.ebankingbackend.repos;

import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String>{
    <T> Page<T> search(String keyword, PageRequest of);

    <T> Page<T> findAll(PageRequest of);
}
