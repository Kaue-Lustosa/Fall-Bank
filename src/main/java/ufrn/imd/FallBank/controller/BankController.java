package ufrn.imd.FallBank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufrn.imd.FallBank.CustomFeatures.Exceptions.BankException;
import ufrn.imd.FallBank.model.Account;
import ufrn.imd.FallBank.service.bank.Bank;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final Bank bank;

    public BankController(Bank bank) {
        this.bank = bank;
    }

    @GetMapping("/public/welcome")
    public String welcome() {
        return "Welcome to Fall Bank!";
    }

    @GetMapping("/private/balance/{id}")
    public double balance(@PathVariable(value = "id", required = true) String id) throws BankException {
        double balance = 0;
        balance = bank.balance(id);
        return balance;
    }

    @PutMapping("/private/depositOp/{id}/{value}")
    public String deposit(@PathVariable(value = "id", required = true) String id, @PathVariable(value = "value", required = true) double value) throws BankException {
        return bank.depositOp(id, value);

    }

    @PutMapping("/private/transferOp/{idOrigin}/{idDestiny}/{value}")
    public String transferOp(@PathVariable String idOrigin, @PathVariable String idDestiny, @PathVariable double value) throws BankException {
        return bank.transferOp(idOrigin, idDestiny, value);
    }

    @PostMapping("/admin/createAccountOp")
    public void createAccountOp(@RequestBody Map<String, String> payload) throws BankException {
        String id = payload.get("id");
        bank.createAccountOp(id);
    }

    @DeleteMapping("/admin/deleteAccountOp/{id}")
    public String deleteAccountOp(@PathVariable(value = "id", required = true) String id) throws BankException {
        return bank.deleteAccountOp(id);
    }

    @GetMapping("/admin/listAccountsOp")
    public List<Account> findAll() {

        return bank.findAll();
    }
}