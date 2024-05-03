package ma.enset.ebankingbackend.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.CreateOperationDto;
import ma.enset.ebankingbackend.dtos.TransferRequestDTO;
import ma.enset.ebankingbackend.dtos.account.AccountDto;
import ma.enset.ebankingbackend.dtos.account.AccountHistoryDto;
import ma.enset.ebankingbackend.services.interfaces.BankAccountService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/accounts")
public class AccountRestController {
    private final BankAccountService bankAccountService;

    @GetMapping("/{accountId}")
    public AccountDto getBankAccount(@PathVariable String accountId) {
        return bankAccountService.get(accountId);
    }

    @GetMapping
    public Page<AccountDto> listAccounts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return bankAccountService.getAll(page, size);
    }

    @GetMapping("/{accountId}/operations")
    public AccountHistoryDto getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }

    @PostMapping("/debit")
    public void debit(@RequestBody CreateOperationDto operationDto) {
        this.bankAccountService.debit(operationDto);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody CreateOperationDto operationDto) {
        this.bankAccountService.credit(operationDto);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        this.bankAccountService.transfer(
                transferRequestDTO.getFromAccountId(),
                transferRequestDTO.getToAccountId(),
                transferRequestDTO.getAmount());
    }
}