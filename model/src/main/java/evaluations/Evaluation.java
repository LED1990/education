package evaluations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Evaluation {

    @Id
    @GeneratedValue
    private Long id;
    private String comment;
    private String CaseNarrative;
    private Set<UndesirableAction> undesirableActions;
}
