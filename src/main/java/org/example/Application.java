package org.example;

import org.example.dao.AccountDAO;
import org.example.entity.Account;
import org.example.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
public class Application implements CommandLineRunner {

    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account("Lesha", 20);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(11));
        bills.add(new Bill(12));
        bills.add(new Bill(13));
        bills.add(new Bill(14));
        bills.add(new Bill(15));
        account.setBills(bills);
        Account savedAccount = accountDAO.save(account);

        Account accountFromDB = accountDAO.findById(savedAccount.getAccountId());
        System.out.println(accountFromDB);
        System.out.println(accountFromDB.getBills());
    }
}
