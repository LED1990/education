package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "undesirableAction", orphanRemoval = true)
    private Set<Classification> classifications = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "undesirableAction", orphanRemoval = true)
    private Set<Indication> indications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Evaluation evaluation;

    public UndesirableAction(String description, Evaluation evaluation) {
        this.description = description;
        this.evaluation = evaluation;
    }

    public void removeIndication(Indication indication) {
        indications.remove(indication);
        indication.setUndesirableAction(null);
    }

    public void removeClassification(Classification classification) {
        classifications.remove(classification);
        classification.setUndesirableAction(null);
    }

    public void addIndication(Indication indication) {
        indications.add(indication);
        indication.setUndesirableAction(this);
    }

    public void addClassification(Classification classification) {
        classifications.add(classification);
        classification.setUndesirableAction(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UndesirableAction that = (UndesirableAction) o;

        return id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;//because DB is setting ID during flush, don't worry if collections are not too big
    }
}
