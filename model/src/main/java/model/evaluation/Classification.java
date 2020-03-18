package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private UndesirableAction undesirableAction;

    public Classification(String code, String name, UndesirableAction undesirableAction) {
        this.code = code;
        this.name = name;
        this.undesirableAction = undesirableAction;
    }
}
