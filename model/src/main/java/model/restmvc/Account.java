package model.restmvc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private Integer accNumber;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Contact> contacts = new HashSet<>();

    //below what should not go to REST API
    private String number = UUID.randomUUID().toString();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Account(String provider, int accNumber, User user) {
        this.provider = provider;
        this.accNumber = accNumber;
        this.user = user;
    }
}
