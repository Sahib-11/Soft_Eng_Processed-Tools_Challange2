package com.accountsService.dao;

import com.accountsService.model.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> getAllAccount();

    Account findAccountById(long id);

    Account saveTAccount(Account account);

    Account updateTAccount(long id, Account account);

    boolean deleteAccountById(long id);
}