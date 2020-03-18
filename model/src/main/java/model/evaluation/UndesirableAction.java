package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UndesirableAction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "undesirableAction")
    private Set<Classification> classifications;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "undesirableAction")
    private Set<Indication> indications;

    @ManyToOne
    private Evaluation evaluation;

    public UndesirableAction(String description, Evaluation evaluation) {
        this.description = description;
        this.evaluation = evaluation;
    }

}
