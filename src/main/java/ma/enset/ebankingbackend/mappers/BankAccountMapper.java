package ma.enset.ebankingbackend.mappers;

import ma.enset.ebankingbackend.dtos.account.AccountDto;
import ma.enset.ebankingbackend.dtos.account.CreateCurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.CreateSavingAccountDto;
import ma.enset.ebankingbackend.dtos.account.CurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.SavingAccountDto;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.SavingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {
    CurrentAccount toEntity(CreateCurrentAccountDto dto);
    SavingAccount toEntity(CreateSavingAccountDto dto);
    AccountDto toDto(BankAccount entity);
    CurrentAccountDto toDto(CurrentAccount entity);
    SavingAccountDto toDto(SavingAccount entity);
}
