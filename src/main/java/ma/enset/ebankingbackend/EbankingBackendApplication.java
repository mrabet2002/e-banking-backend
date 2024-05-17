package ma.enset.ebankingbackend;

import ma.enset.ebankingbackend.config.JwtConfig;
import ma.enset.ebankingbackend.dtos.RegisterUserDto;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.enums.AccountStatus;
import ma.enset.ebankingbackend.repos.BankAccountRepository;
import ma.enset.ebankingbackend.repos.CustomerRepository;
import ma.enset.ebankingbackend.repos.OperationRepository;
import ma.enset.ebankingbackend.services.interfaces.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			CustomerRepository customerRepository,
			BankAccountRepository bankAccountRepository,
			OperationRepository operationRepository,
			AuthService authService
	) {
		return args -> {
//			Generate 3 Customers With Real Names
//			List<Customer> customers = Stream.of("Yassine", "Oussama", "Younes")
//					.map(name -> customerRepository.save(new Customer(null, name, name + "@gmail.com", null)))
//					.toList();
//
//			customers.forEach(customer -> {
////				Generate Saving Accounts for each Customer
//				SavingAccount sAccount = new SavingAccount();
//				sAccount.setId(UUID.randomUUID().toString());
//				sAccount.setCurrency("MAD");
//				sAccount.setStatus(AccountStatus.CREATED);
//				sAccount.setCreatedAt(LocalDateTime.now());
//				sAccount.setInterestRate(0.03);
//				sAccount.setBalance(1000d);
//				sAccount.setCustomer(customer);
//				bankAccountRepository.save(sAccount);
//
////				Generate Current Accounts for each Customer
//				CurrentAccount cAccount = new CurrentAccount();
//				cAccount.setId(UUID.randomUUID().toString());
//				cAccount.setCurrency("MAD");
//				cAccount.setStatus(AccountStatus.CREATED);
//				cAccount.setCreatedAt(LocalDateTime.now());
//				cAccount.setOverdraft(1000d);
//				cAccount.setBalance(1000d);
//				cAccount.setCustomer(customer);
//				bankAccountRepository.save(cAccount);
//			});

//			Generating 3 users
//			Stream.of(
//					"Yassine", "Oussama", "Younes", "Ahmed"
//			).forEach(name -> {
//				RegisterUserDto user = new RegisterUserDto();
//				user.setUsername(name.toLowerCase());
//				user.setName(name);
//				user.setPassword("password");
//				authService.registerUser(user);
//			});
		};
	}

}
