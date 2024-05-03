package ma.enset.ebankingbackend.services.interfaces;

import ma.enset.ebankingbackend.dtos.CreateOperationDto;
import ma.enset.ebankingbackend.dtos.account.AccountDto;
import ma.enset.ebankingbackend.dtos.account.AccountHistoryDto;
import ma.enset.ebankingbackend.dtos.account.CreateBankAccountDto;
import ma.enset.ebankingbackend.dtos.account.CreateCurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.CreateSavingAccountDto;
import ma.enset.ebankingbackend.dtos.account.CurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.SavingAccountDto;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.enums.AccountStatus;
import org.springframework.data.domain.Page;

public interface BankAccountService {
    //    CRUD Operations
    AccountDto createCurrentAccount(CreateCurrentAccountDto accountDto);

    SavingAccount createSavingAccount(CreateSavingAccountDto accountDto);

    void updateStatus(String id, AccountStatus newStatus);

    void delete(String id);

    AccountDto get(String id);

    Page<AccountDto> getAll(int page, int size);

    Page<CurrentAccountDto> getCurrentAccounts(int page, int size);

    Page<SavingAccountDto> getSavingAccounts(int page, int size);

    Page<AccountDto> search(String keyword, int page, int size);

    void debit(CreateOperationDto operationDto);

    void credit(CreateOperationDto  operationDto);

    void transfer(String fromId, String toId, double amount);

    AccountHistoryDto getAccountHistory(String accountId, int page, int size);
}
