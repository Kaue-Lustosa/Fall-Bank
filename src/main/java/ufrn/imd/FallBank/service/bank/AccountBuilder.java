package ufrn.imd.FallBank.service.bank;

import ufrn.imd.FallBank.model.Account;

public class AccountBuilder {
    private final Account account;

    AccountBuilder(){
        this.account = new Account();
    }

    public AccountBuilder setBalance(float balance){
        account.setBalance(balance);
        return this;
    }

    public AccountBuilder setId(String idAccount){
        account.setId(idAccount);
        return this;
    }

    public Account createAccountOp(){
        return account;
    }
}
