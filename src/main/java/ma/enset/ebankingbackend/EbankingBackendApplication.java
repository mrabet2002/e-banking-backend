package ma.enset.ebankingbackend;

import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.enums.AccountStatus;
import ma.enset.ebankingbackend.repos.BankAccountRepository;
import ma.enset.ebankingbackend.repos.CustomerRepository;
import ma.enset.ebankingbackend.repos.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			CustomerRepository customerRepository,
			BankAccountRepository bankAccountRepository,
			OperationRepository operationRepository
	) {
		return args -> {
//			Generate 3 Customers With Real Names
			List<Customer> customers = Stream.of("Yassine", "Oussama", "Younes")
					.map(name -> customerRepository.save(new Customer(null, name, name + "@gmail.com", null)))
					.toList();

			customers.forEach(customer -> {
//				Generate Saving Accounts for each Customer
				SavingAccount sAccount = new SavingAccount();
				sAccount.setId(UUID.randomUUID().toString());
				sAccount.setCurrency("MAD");
				sAccount.setStatus(AccountStatus.CREATED);
				sAccount.setCreatedAt(LocalDateTime.now());
				sAccount.setInterestRate(0.03);
				sAccount.setBalance(1000d);
				sAccount.setCustomer(customer);
				bankAccountRepository.save(sAccount);

//				Generate Current Accounts for each Customer
				CurrentAccount cAccount = new CurrentAccount();
				cAccount.setId(UUID.randomUUID().toString());
				cAccount.setCurrency("MAD");
				cAccount.setStatus(AccountStatus.CREATED);
				cAccount.setCreatedAt(LocalDateTime.now());
				cAccount.setOverdraft(1000d);
				cAccount.setBalance(1000d);
				cAccount.setCustomer(customer);
				bankAccountRepository.save(cAccount);
			});

		};
	}

}
