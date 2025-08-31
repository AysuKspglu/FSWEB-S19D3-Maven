package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(Long id);
    Account create(Account a);
    Account update(Long id, Account a);
    void delete(Long id);

    // Testin istediği
    Account save(Account account);
}
