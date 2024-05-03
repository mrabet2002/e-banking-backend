package ma.enset.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.services.interfaces.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping("")
    public Page<CustomerDto> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return customerService.getAll(page, size);
    }

    @GetMapping("/search")
    public Page<CustomerDto> searchCustomers(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return customerService.search(keyword, page, size);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(
            @PathVariable(name = "id") Long customerId
    ) {
        return customerService.get(customerId);
    }

    @PostMapping("")
    public CustomerDto saveCustomer(@RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
    }
}