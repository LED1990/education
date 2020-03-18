package evaluations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UndesirableAction {

    public UndesirableAction(String description) {
        this.description = description;
    }

    private Long id;
    private String description;
    @Transient
    private Set<Classification> classifications;
    @Transient
    private Set<Indication> indications;

}
