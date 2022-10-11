package com.accountsService.services;

import com.accountsService.dao.AccountDAO;
import com.accountsService.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Transactional
    public List<Account> findAllAccounts() {
        return accountDAO.getAllAccount();
    }

    @Transactional
    public Account findAccountById(long id) {
        return accountDAO.findAccountById(id);
    }

    @Transactional
    public Account saveAccount(Account account) {
        return accountDAO.saveTAccount(account);
    }

    @Transactional
    public Account updateAccount(long id, Account account) {
        return accountDAO.updateTAccount(id, account);
    }

    @Transactional
    public boolean deleteAccountById(long id) {
        return accountDAO.deleteAccountById(id);
    }
}