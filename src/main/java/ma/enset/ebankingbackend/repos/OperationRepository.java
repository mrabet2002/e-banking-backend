package ma.enset.ebankingbackend.repos;

import ma.enset.ebankingbackend.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long>{
    Page<Operation> findByBankAccount_IdOrderByDateDesc(String accountId, PageRequest of);
}
