package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private String caseNarrative;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluation")
    private Set<UndesirableAction> undesirableActions;

    public Evaluation(String comment, String caseNarrative, Set<UndesirableAction> undesirableActions) {
        this.comment = comment;
        this.caseNarrative = caseNarrative;
        this.undesirableActions = undesirableActions;
    }

    public void removeUndesirableAction(UndesirableAction undesirableAction){
        undesirableActions.remove(undesirableAction);
        undesirableAction.setEvaluation(null);
    }

    public void addUndesirableAction(UndesirableAction undesirableAction){
        undesirableActions.add(undesirableAction);
        undesirableAction.setEvaluation(this);
    }
}
