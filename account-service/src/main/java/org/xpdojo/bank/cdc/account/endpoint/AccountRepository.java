package org.xpdojo.bank.cdc.account.endpoint;

import org.xpdojo.bank.cdc.account.domain.Account;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.xpdojo.bank.cdc.account.domain.Account.anAccountWith;
import static org.xpdojo.bank.cdc.account.domain.Account.anEmptyAccount;
import static org.xpdojo.bank.cdc.account.domain.Money.anAmountOf;

public class AccountRepository {

    private static final Long MIN = 12340000L;
    private static final Long MAX = 12349999L;
    private final Map<Long, Account> accounts = new HashMap<>();

    {
        Account account = anAccountWith(30001234L, anAmountOf(100.0d));
        account.setAccountDescription("My private account for dangerous gambling");
        account.setOverdraftFacility(anAmountOf(5000D));
        account.withdraw(anAmountOf(1000.0d));
        accounts.put(account.getAccountNumber(), account);

        Account otherAccount = anEmptyAccount(30005678L);
        otherAccount.setAccountDescription("My steady savings account");
        otherAccount.deposit(anAmountOf(120.0d));
        accounts.put(otherAccount.getAccountNumber(), otherAccount);

        Account pactAccount = anAccountWith(30002468L, anAmountOf(1000.0D));
        pactAccount.setAccountDescription("Account used for pact testing");
        pactAccount.setOverdraftFacility(anAmountOf(23.0D));
        accounts.put(pactAccount.getAccountNumber(), pactAccount);
    }

    public Account create() {
        Account account = anEmptyAccount(createRandomBankAccountNumber());
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public Collection<Account> readAccounts() {
        return accounts.values();
    }

    public Account getById(Long accountId) {
        return accounts.get(accountId);
    }

    private final Long createRandomBankAccountNumber() {
        return MIN + (long) (Math.random() * (MAX - MIN));
    }

    public void update(Account account) {
        accounts.replace(account.getAccountNumber(), account);
    }
}