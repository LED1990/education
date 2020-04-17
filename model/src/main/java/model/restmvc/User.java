package model.restmvc;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true) //lazy by default
    private Set<Account> accounts = new HashSet<>();

    //below what should not go to REST API
    private String password = UUID.randomUUID().toString();

    public User(String name, String lastName, Set<Account> accounts) {
        this.name = name;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        accounts.add(account);
        account.setUser(this);
    }

    public void removeAccount(Account account){
        accounts.remove(account);
        account.setUser(null);
    }
}
