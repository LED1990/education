package model.restmvc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "graph.UserAccountContacts",
        attributeNodes = @NamedAttributeNode(value = "accounts", subgraph = "subgraph.contacts"),
        subgraphs = {@NamedSubgraph(name = "subgraph.contacts", attributeNodes = @NamedAttributeNode("contacts"))}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //lazy by default
//    @BatchSize(size = 5)//to reduce N+1 problem. Not perfect, better write custom query with fetch join or Entity Graph
    private Set<Account> accounts;

    //below what should not go to REST API
    private String password = UUID.randomUUID().toString();

    public User(String name, String lastName, Set<Account> accounts) {
        this.name = name;
        this.lastName = lastName;
        this.accounts = accounts;
    }
}
