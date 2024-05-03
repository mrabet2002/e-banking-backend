package ma.enset.ebankingbackend.mappers;

import ma.enset.ebankingbackend.dtos.OperationDto;
import ma.enset.ebankingbackend.entities.Operation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    OperationDto toOperationDto(Operation customer);

    List<OperationDto> toOperationDtoList(List<Operation> customers);
}
