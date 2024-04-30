package ma.enset.ebankingbackend.repos;

import ma.enset.ebankingbackend.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long>{
}
