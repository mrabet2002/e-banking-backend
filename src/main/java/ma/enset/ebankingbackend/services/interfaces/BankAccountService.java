package ma.enset.ebankingbackend.services.interfaces;

import ma.enset.ebankingbackend.dtos.account.AccountDto;
import ma.enset.ebankingbackend.dtos.account.CreateBankAccountDto;
import ma.enset.ebankingbackend.dtos.account.CurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.SavingAccountDto;
import ma.enset.ebankingbackend.enums.AccountStatus;
import org.springframework.data.domain.Page;

public interface BankAccountService {
    //    CRUD Operations
    AccountDto create(CreateBankAccountDto accountDto);

    void updateStatus(Long id, AccountStatus newStatus);

    void delete(Long id);

    AccountDto get(Long id);

    Page<AccountDto> getAll(int page, int size);

    Page<CurrentAccountDto> getCurrentAccounts(int page, int size);

    Page<SavingAccountDto> getSavingAccounts(int page, int size);

    Page<AccountDto> search(String keyword, int page, int size);

}
