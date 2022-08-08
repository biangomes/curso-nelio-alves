package secao14.heranca.application;

import secao14.heranca.entities.Account;
import secao14.heranca.entities.BusinessAccount;
import secao14.heranca.entities.SavingsAccount;

public class Program2 {
    public static void main(String[] args) {

        //Account acc1 = new Account(1001, "Alex", 1000.00);
        //acc1.withdraw(200.00);
        //System.out.println(acc1.getBalance());

        Account acc2 = new SavingsAccount(1002, "Rose", 1000.00, 0.01);
        acc2.withdraw(200.00);
        System.out.println(acc2.getBalance());

        Account acc3 = new BusinessAccount(1003, "Bob", 1000.00, 500.0);
        acc3.withdraw(200.00);
        System.out.println(acc3.getBalance());
    }
}
