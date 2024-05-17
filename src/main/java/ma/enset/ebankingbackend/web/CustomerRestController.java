package ma.enset.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.services.interfaces.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/customers")
@PreAuthorize("isAuthenticated()")
public class CustomerRestController {
    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<Page<CustomerDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(customerService.getAll(page, size));
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
    public ResponseEntity<CustomerDto> getCustomer(
            @PathVariable(name = "id") Long customerId
    ) {
        return ResponseEntity.ok(customerService.get(customerId));
    }

    @PostMapping("")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.add(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.update(id, customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}