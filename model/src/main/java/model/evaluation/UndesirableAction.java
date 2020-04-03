package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UndesirableAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "undesirableAction")
    private Set<Classification> classifications = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "undesirableAction")
    private Set<Indication> indications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Evaluation evaluation;

    public UndesirableAction(String description, Evaluation evaluation) {
        this.description = description;
        this.evaluation = evaluation;
    }

}
