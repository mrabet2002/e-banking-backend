package ma.enset.ebankingbackend.services.impls;

import lombok.RequiredArgsConstructor;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.mappers.CustomerMapper;
import ma.enset.ebankingbackend.repos.CustomerRepository;
import ma.enset.ebankingbackend.services.interfaces.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto add(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail()))
            throw new RuntimeException("Email already exists");
        return customerMapper.toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public void update(Long id, Customer customer) {
        Customer customerToUpdate = this.getEntity(id);
        customer.setId(customerToUpdate.getId());
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDto get(Long id) {
        return customerMapper.toCustomerDto(customerRepository.findById(id).orElse(null));
    }

    @Override
    public Customer getEntity(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Page<CustomerDto> getAll(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page, size))
                .map(customerMapper::toCustomerDto);
    }

    @Override
    public Page<CustomerDto> search(String keyword, int page, int size) {
        return customerRepository.search(keyword, PageRequest.of(page, size))
                .map(customerMapper::toCustomerDto);
    }
}
