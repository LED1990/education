package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Indication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String mdeicineName;
    private String info;
    @ManyToOne
    private UndesirableAction undesirableAction;

    public Indication(String mdeicineName, String info, UndesirableAction undesirableAction) {
        this.mdeicineName = mdeicineName;
        this.info = info;
        this.undesirableAction = undesirableAction;
    }
}
