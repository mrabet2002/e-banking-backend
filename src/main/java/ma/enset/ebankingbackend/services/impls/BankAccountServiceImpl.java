package ma.enset.ebankingbackend.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.enset.ebankingbackend.dtos.CreateOperationDto;
import ma.enset.ebankingbackend.dtos.account.AccountDto;
import ma.enset.ebankingbackend.dtos.account.AccountHistoryDto;
import ma.enset.ebankingbackend.dtos.account.CreateCurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.CreateSavingAccountDto;
import ma.enset.ebankingbackend.dtos.account.CurrentAccountDto;
import ma.enset.ebankingbackend.dtos.account.SavingAccountDto;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Operation;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.enums.AccountStatus;
import ma.enset.ebankingbackend.enums.OperationType;
import ma.enset.ebankingbackend.exceptions.InsufficientBalanceException;
import ma.enset.ebankingbackend.exceptions.RecordNotFoundException;
import ma.enset.ebankingbackend.mappers.BankAccountMapper;
import ma.enset.ebankingbackend.mappers.CustomerMapper;
import ma.enset.ebankingbackend.mappers.OperationMapper;
import ma.enset.ebankingbackend.repos.BankAccountRepository;
import ma.enset.ebankingbackend.repos.OperationRepository;
import ma.enset.ebankingbackend.services.interfaces.BankAccountService;
import ma.enset.ebankingbackend.services.interfaces.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final OperationRepository operationRepository;
    private final BankAccountMapper bankAccountMapper;
    private final CustomerService customerService;
    private final OperationMapper operationMapper;
    private final CustomerMapper customerMapper;

    @Override
    public AccountDto createCurrentAccount(CreateCurrentAccountDto accountDto) {
        CurrentAccount bankAccount = bankAccountMapper.toEntity(accountDto);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(LocalDateTime.now());
        bankAccount.setStatus(AccountStatus.CREATED);
        bankAccount.setCustomer(customerService.getEntity(accountDto.getCustomerId()));
        return bankAccountMapper.toDto(bankAccount);
    }

    @Override
    public SavingAccount createSavingAccount(CreateSavingAccountDto accountDto) {
        SavingAccount savingAccount = bankAccountMapper.toEntity(accountDto);
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(LocalDateTime.now());
        savingAccount.setStatus(AccountStatus.CREATED);
        savingAccount.setCustomer(customerService.getEntity(accountDto.getCustomerId()));
        return bankAccountRepository.save(savingAccount);
    }

    private BankAccount initBankAccount(BankAccount bankAccount, Long customerId) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(LocalDateTime.now());
        bankAccount.setStatus(AccountStatus.CREATED);
        bankAccount.setCustomer(customerService.getEntity(customerId));
        return bankAccount;
    }

    @Override
    public void updateStatus(String id, AccountStatus newStatus) {
        BankAccount bankAccount = getEntity(id);
        bankAccount.setStatus(newStatus);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void delete(String id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    public AccountDto get(String id) {
        return bankAccountMapper.toDto(getEntity(id));
    }

    public BankAccount getEntity(String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Account not found"));
    }

    @Override
    public Page<AccountDto> getAll(int page, int size) {
        Page<BankAccount> bankAccounts = bankAccountRepository.findAll(PageRequest.of(page, size));
        return bankAccounts.map(bankAccountMapper::toDto);
    }

    @Override
    public Page<CurrentAccountDto> getCurrentAccounts(int page, int size) {
        return null;
//        Page<CurrentAccount> currentAccounts = bankAccountRepository.findAll(PageRequest.of(page, size));
//        return currentAccounts.map(bankAccountMapper::toDto);
    }

    @Override
    public Page<SavingAccountDto> getSavingAccounts(int page, int size) {
        return null;
//        Page<SavingAccount> savingAccounts = bankAccountRepository.findAll(PageRequest.of(page, size));
//        return savingAccounts.map(bankAccountMapper::toDto);
    }

    @Override
    public Page<AccountDto> search(String keyword, int page, int size) {
        Page<BankAccount> bankAccounts = bankAccountRepository.search(keyword, PageRequest.of(page, size));
        return bankAccounts.map(bankAccountMapper::toDto);
    }

    @Override
    @Transactional
    public void debit(CreateOperationDto  operationDto) {
        BankAccount bankAccount = getEntity(operationDto.getAccountId());
        Operation operation = makeOperation(operationDto, OperationType.DEBIT);
        // Check if the account has enough balance
        if (bankAccount.getBalance() < operation.getAmount())
            throw new InsufficientBalanceException("Insufficient balance");
        bankAccount.setBalance(bankAccount.getBalance() - operation.getAmount());
        bankAccountRepository.save(bankAccount);
    }

    @Override
    @Transactional
    public void credit(CreateOperationDto  operationDto) {

        BankAccount bankAccount = getEntity(operationDto.getAccountId());
        Operation operation = makeOperation(operationDto, OperationType.CREDIT);
        bankAccount.setBalance(bankAccount.getBalance() + operation.getAmount());
        bankAccountRepository.save(bankAccount);
    }

    private Operation makeOperation(CreateOperationDto operationDto, OperationType type) {
        BankAccount account = getEntity(operationDto.getAccountId());
        Operation operation = new Operation();
        operation.setBankAccount(account);
        operation.setAmount(operationDto.getAmount());
        operation.setDescription(operationDto.getDescription());
        operation.setType(type);
        operation.setDate(LocalDateTime.now());
        return operationRepository.save(operation);
    }

    @Override
    @Transactional
    public void transfer(String fromId, String toId, double amount) {
//        Doing a credit and debit operations
        debit(CreateOperationDto
                .builder()
                .accountId(fromId)
                .amount(amount)
                .description("Transfer to " + toId)
                .build());

        credit(CreateOperationDto
                .builder()
                .accountId(toId)
                .amount(amount)
                .description("Transfer from " + fromId)
                .build());
    }

    @Override
    public AccountHistoryDto getAccountHistory(String accountId, int page, int size) {
        BankAccount bankAccount = getEntity(accountId);
        Page<Operation> operations = operationRepository.findByBankAccount_IdOrderByDateDesc(
                accountId, PageRequest.of(page, size));

        return AccountHistoryDto.builder()
                .id(bankAccount.getId())
                .customer(customerMapper.toCustomerDto(bankAccount.getCustomer()))
                .operations(operations.map(operationMapper::toOperationDto))
                .balance(bankAccount.getBalance())
                .status(bankAccount.getStatus())
                .currency(bankAccount.getCurrency())
                .build();
    }
}
