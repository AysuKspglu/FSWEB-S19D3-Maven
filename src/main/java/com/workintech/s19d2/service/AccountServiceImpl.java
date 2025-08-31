package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;

    @Override
    public List<Account> findAll() { return repo.findAll(); }

    @Override
    public Account findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Account not found: " + id));
    }

    @Override
    public Account create(Account a) {
        a.setId(null);
        return repo.save(a);
    }

    @Override
    public Account update(Long id, Account a) {
        Account db = findById(id);
        db.setName(a.getName());
        return repo.save(db);
    }

    @Override
    public void delete(Long id) { repo.deleteById(id); }

    // Testin beklediği metot
    @Override
    public Account save(Account account) {
        return repo.save(account);
    }
}
