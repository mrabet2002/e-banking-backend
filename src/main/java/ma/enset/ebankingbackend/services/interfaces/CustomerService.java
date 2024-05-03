package ma.enset.ebankingbackend.services.interfaces;

import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.entities.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDto add(Customer customer);

    void update(Long id, Customer customer);

    void delete(Long id);

    CustomerDto get(Long id);

    Customer getEntity(Long id);

    Page<CustomerDto> getAll(int page, int size);

    Page<CustomerDto> search(String keyword, int page, int size);
}
