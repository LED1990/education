package evaluations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UndesirableAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//todo na psql zamienic
    private Long id;
    private String description;
    @Transient
    private Set<Classification> classifications;
    @Transient
    private Set<Indication> indications;

    @ManyToOne
    private Evaluation evaluation;

    public UndesirableAction(String description, Evaluation evaluation) {
        this.description = description;
        this.evaluation = evaluation;
    }

}
