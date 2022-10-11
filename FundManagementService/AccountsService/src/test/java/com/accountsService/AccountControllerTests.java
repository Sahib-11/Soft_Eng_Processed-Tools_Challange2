package com.accountsService;

import com.accountsService.controller.AccountController;
import com.accountsService.model.Account;
import com.accountsService.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTests {

    @MockBean
    AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        Date date = new Date();
        Account account = new Account(01L,"Loan", "98421", "testAcc", "2000", date);
        List<Account> accounts = Arrays.asList(account);

        Mockito.when(accountService.findAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/account/accounts"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", Matchers.hasSize(1)))
                        .andExpect(jsonPath("$[0].accountType", Matchers.is("Loan")));
    }

    @Test
    public void getPerson() throws Exception {
        Date date = new Date();
        Account account = new Account(01L,"Loan", "98421", "testAcc", "2000", date);

        Mockito.when(accountService.findAccountById(2)).thenReturn(account);

        mockMvc.perform(get("/account/{id}", 2))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.accountType", Matchers.is("Loan")));
    }

    @Test
    public void createPerson() throws Exception {
        Date date = new Date();
        Account account = new Account(01L,"Loan", "98421", "testAcc", "2000", date);

        Mockito.when(accountService.saveAccount(account)).thenReturn(account);

        mockMvc.perform(post("/account/account")
                        .content(asJsonString(account))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.accountType", Matchers.is("Loan")));

    }

    @Test
    public void updatePerson() throws Exception {
        Date date = new Date();
        Account account = new Account(01L,"Loan", "98421", "testAcc", "2000", date);

        Mockito.when(accountService.updateAccount(1, account)).thenReturn(account);

        mockMvc.perform(put("/account/{id}", 1)
                        .content(asJsonString(account))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.accountType", Matchers.is("Loan")));

    }

    @Test
    public void deletePersonById() throws Exception {

        Mockito.when(accountService.deleteAccountById(1)).thenReturn(true);


        mockMvc.perform(delete("/account/{id}", 1))
                .andExpect(status().isAccepted());

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
