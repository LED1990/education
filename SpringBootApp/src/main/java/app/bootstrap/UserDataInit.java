package app.bootstrap;

import db.restmvc.UserRepository;
import lombok.extern.slf4j.Slf4j;
import model.restmvc.Account;
import model.restmvc.Contact;
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

        User user1 = new User("John", "Whoo", null);

        Account account1 = new Account("Facebook", 2, user1);
        Account account2 = new Account("Oracle", 5, user1);
        Account account3 = new Account("Facebook", 1, user1);
        Account account4 = new Account("Udemy", 5, user1);
        user1Acc.add(account1);
        user1Acc.add(account2);
        user1Acc.add(account3);
        user1Acc.add(account4);
        user1Acc.add(new Account("Provider", 4, user1));
        user1Acc.add(new Account("Provider2", 7, user1));
        user1Acc.add(new Account("Provider3", 8, user1));
        user1Acc.add(new Account("Provider4", 11, user1));
        user1Acc.add(new Account("Provider5", 24, user1));
        user1Acc.add(new Account("Provider6", 24, user1));
        user1Acc.add(new Account("Provider7", 14, user1));
        user1Acc.add(new Account("Provider8", 3, user1));

        user1Acc.forEach(account -> {
            account.getContacts().add(new Contact("email@gamil.com", account));
            account.getContacts().add(new Contact("email@gamilX.com", account));
            account.getContacts().add(new Contact("email@gamilXX.com", account));
        });

        user1.setAccounts(user1Acc);

        userRepository.save(user1);
    }
}
