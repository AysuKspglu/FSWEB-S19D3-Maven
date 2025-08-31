package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workintech/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping("/")
    public List<Account> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Account one(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Account create(@RequestBody Account a) { return service.create(a); }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account a) { return service.update(id, a); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
