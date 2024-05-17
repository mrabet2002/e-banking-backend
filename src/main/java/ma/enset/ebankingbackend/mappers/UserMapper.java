package ma.enset.ebankingbackend.mappers;

import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    // Concat firstName and lastName to name
    User toUser(RegisterUserDto registerUserDto);
}
