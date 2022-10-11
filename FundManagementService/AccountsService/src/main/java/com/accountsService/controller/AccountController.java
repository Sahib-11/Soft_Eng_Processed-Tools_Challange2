package com.accountsService.controller;

import com.accountsService.model.Account;
import com.accountsService.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * Return account list
     * @return
     */
    @GetMapping(path = "/accounts")
    public ResponseEntity<List<Account>> findAll(){
        try {
            return new ResponseEntity<>(accountService.findAllAccounts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Returns account object by taking id as input
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") long id) {
        try {
            Account account = accountService.findAccountById(id);

            // Returning data
            if (account != null) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new account
     * @param account
     * @return
     */
    @PostMapping(path = "/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createAccount = accountService.saveAccount(account);
        return new ResponseEntity<>(createAccount, HttpStatus.OK);
    }

    /**
     * Updates account
     * @param id
     * @param account
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        Account updateAccount = accountService.updateAccount(id, account);
        if(updateAccount != null) {
            return new ResponseEntity<>(updateAccount, HttpStatus.CREATED);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Deletes account by taking in id as input
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteAccountById(@PathVariable("id") long id) {
        try {
            boolean deleted = accountService.deleteAccountById(id);

            // Deleted account response
            if (deleted == true) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}