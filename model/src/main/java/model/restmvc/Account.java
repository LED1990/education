package model.restmvc;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private Integer accNumber;

    //below what should not go to REST API
    private String number = UUID.randomUUID().toString();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Account(String provider, int accNumber, User user) {
        this.provider = provider;
        this.accNumber = accNumber;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id != null && id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
