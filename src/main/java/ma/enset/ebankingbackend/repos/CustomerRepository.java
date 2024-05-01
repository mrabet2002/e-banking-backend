package ma.enset.ebankingbackend.repos;

import ma.enset.ebankingbackend.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    boolean existsByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:keyword% OR c.email LIKE %:keyword%")
    Page<Customer> search(String keyword, Pageable pageable);
}
