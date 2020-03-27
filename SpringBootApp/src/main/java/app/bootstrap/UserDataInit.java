package app.bootstrap;

import db.restmvc.UserRepository;
import lombok.extern.slf4j.Slf4j;
import model.restmvc.Account;
import model.restmvc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class UserDataInit implements CommandLineRunner{

    private UserRepository userRepository;

    @Autowired
    public UserDataInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        log.info("Bootstrapping user data");
        Set<Account> user1Acc = new HashSet<>();
        Set<Account> user2Acc = new HashSet<>();

        User user1 = new User("John", "Whoo", null);
        User user2 = new User("James", "Bond", null);

        Account account1 = new Account("Facebook", 2, user1);
        Account account2 = new Account("Oracle", 5, user1);
        Account account3 = new Account("Facebook", 1, user2);
        Account account4 = new Account("Udemy", 5, user2);
        user1Acc.add(account1);
        user1Acc.add(account2);

        user2Acc.add(account3);
        user2Acc.add(account4);

        user1.setAccounts(user1Acc);
        user2.setAccounts(user2Acc);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
