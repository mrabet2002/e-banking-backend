package ma.enset.ebankingbackend.services.interfaces;

import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.entities.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
//    CRUD Operations
    public CustomerDto add(Customer customer);
    public void update(Long id, Customer customer);
    public void delete(Long id);
    public CustomerDto get(Long id);

    Customer getEntity(Long id);

    public Page<CustomerDto> getAll(int page, int size);

    Page<CustomerDto> search(String keyword, int page, int size);
}
