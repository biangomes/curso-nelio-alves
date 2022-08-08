package secao14.heranca.application;

import secao14.heranca.entities.Account;
import secao14.heranca.entities.SavingsAccount;

public class Program3 {
    public static void main(String[] args) {

        // POLIMORFISMO
        //Account x = new Account(1020, "Alex", 1000.0);
        Account y = new SavingsAccount(1023, "Maria", 1000.00, 0.01);

        //x.withdraw(50.0);
        y.withdraw(50.0);

        //System.out.println(x.getBalance());
        System.out.println(y.getBalance());

    }
}
