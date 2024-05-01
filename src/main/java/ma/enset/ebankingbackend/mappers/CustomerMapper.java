package ma.enset.ebankingbackend.mappers;

import lombok.NoArgsConstructor;
import ma.enset.ebankingbackend.dtos.CustomerDto;
import ma.enset.ebankingbackend.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toCustomerDto(Customer customer);

    List<CustomerDto> toCustomerDtoList(List<Customer> customers);
}
