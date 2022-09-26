package com.example.bank_account_kata;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bank_account_kata.domain.account.service.AccountService;
import com.example.bank_account_kata.domain.client.model.Client;
import com.example.bank_account_kata.domain.client.service.ClientService;
import com.example.bank_account_kata.domain.history.model.BankOperation;
import com.example.bank_account_kata.domain.history.model.History;
import com.example.bank_account_kata.domain.history.service.HistoryService;
import com.example.bank_account_kata.infrastructure.persistence.entity.AccountEntity;
import com.example.bank_account_kata.infrastructure.persistence.entity.ClientEntity;
import com.example.bank_account_kata.infrastructure.persistence.repository.AccountRepository;
import com.example.bank_account_kata.infrastructure.persistence.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BankAccountKataApplicationTests {

	@Autowired
	private ClientService clientService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	@Transactional
	public void DepositMoneyShouldIncrementBalance() {
		// Arrange
		Client client = new Client("Vann", "Vann");
		clientService.createClient(client);

		List<ClientEntity> clients = clientRepository.findAll();
		assertThat(clients.size()).isEqualTo(1);
		ClientEntity clientEntity = clients.get(0);
		String clientId = clientEntity.getId();

		accountService.addAccountToClient(clientId);
		List<AccountEntity> accounts = accountRepository.findAll();
		assertThat(accounts.size()).isEqualTo(1);
		String accountId = accounts.get(0).getId();

		Optional<Client> clientOptional = clientService.getClientById(clientId);
		assertThat(clientOptional).isPresent();
		Client client1 = clientOptional.get();
		assertThat(client1.getAccounts().size()).isEqualTo(1);
		assertThat(client1.getAccounts().get(0).getId()).isEqualTo(accountId);

		// Act
		accountService.deposit(accountId, BigDecimal.valueOf(50));

		// Assert
		assertThat(accountService.getBalance(accountId)).isPresent();
		assertThat(accountService.getBalance(accountId).get()).isEqualTo(BigDecimal.valueOf(50));
	}

	@Test
	@Transactional
	public void WithdrawMoneyShouldDiminishBalance() {
		// Arrange
		Client client = new Client("Vann", "Vann");
		clientService.createClient(client);

		List<ClientEntity> clients = clientRepository.findAll();
		ClientEntity clientEntity = clients.get(0);
		String clientId = clientEntity.getId();

		accountService.addAccountToClient(clientId);
		List<AccountEntity> accounts = accountRepository.findAll();
		String accountId = accounts.get(0).getId();

		// Act
		accountService.deposit(accountId, BigDecimal.valueOf(50));
		accountService.withdraw(accountId, BigDecimal.valueOf(30));

		// Assert
		assertThat(accountService.getBalance(accountId)).isPresent();
		assertThat(accountService.getBalance(accountId).get()).isEqualTo(BigDecimal.valueOf(20));
	}

	@Test
	@Transactional
	public void OperationsShouldBeSavedInHistory() {
		// Arrange
		Client client = new Client("Vann", "Vann");
		clientService.createClient(client);

		List<ClientEntity> clients = clientRepository.findAll();
		ClientEntity clientEntity = clients.get(0);
		String clientId = clientEntity.getId();

		accountService.addAccountToClient(clientId);
		List<AccountEntity> accounts = accountRepository.findAll();
		String accountId = accounts.get(0).getId();

		// Act
		accountService.deposit(accountId, BigDecimal.valueOf(50));
		accountService.withdraw(accountId, BigDecimal.valueOf(30));

		// Assert
		List<History> histories = historyService.getHistoryForAccount(accountId);
		assertThat(histories.size()).isEqualTo(2);
		assertThat(histories.get(0).getOperation()).isEqualTo(BankOperation.WITHDRAW);
		assertThat(histories.get(1).getOperation()).isEqualTo(BankOperation.DEPOSIT);
	}

}
