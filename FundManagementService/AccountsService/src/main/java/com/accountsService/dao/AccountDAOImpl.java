package com.accountsService.dao;

import com.accountsService.model.Account;
import com.accountsService.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private AccountRepo accountRepo;

    /**
     * Finds all account
     * @return
     */
    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    /**
     * Finds account by taking id as input
     * @param id
     * @return
     */
    @Override
    public Account findAccountById(long id) {
        Optional<Account> accountObj = accountRepo.findById(id);
        if(accountObj != null) {
            return accountObj.get();
        }
        return null;
    }

    /**
     * Saves account
     * @param account
     * @return
     */
    @Override
    public Account saveTAccount(Account account) {
        Account createAccount = accountRepo
                .save(Account.builder()
                        .accountType(account.getAccountType())
                        .accountNumber(account.getAccountNumber())
                        .accountName(account.getAccountName())
                        .balance(account.getBalance())
                        .build());
        return createAccount;
    }

    /**
     * Updates account
     * @param id
     * @param account
     * @return
     */
    public Account updateTAccount(long id, Account account) {
        Optional<Account> accountObj = accountRepo.findById(id);
        if(accountObj != null) {
            Account updateAccount = accountObj.get();
            updateAccount.setAccountType(account.getAccountType());
            updateAccount.setAccountNumber(account.getAccountNumber());
            updateAccount.setAccountName(account.getAccountName());
            updateAccount.setBalance(account.getBalance());
            accountRepo.save(updateAccount);
            return updateAccount;
        }
        return null;
    }


    /**
     * Deletes account by taking id as input
     * @param id
     * @return
     */
    @Override
    public boolean deleteAccountById(long id) {
        Optional<Account> accountObj = accountRepo.findById(id);
        if(accountObj != null) {
            accountRepo.deleteById(id);
            return true;
        }
        return false;
    }
}