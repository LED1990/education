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
public class Indication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private String info;
    @ManyToOne(fetch = FetchType.LAZY)
    private UndesirableAction undesirableAction;

    public Indication(String medicineName, String info, UndesirableAction undesirableAction) {
        this.medicineName = medicineName;
        this.info = info;
        this.undesirableAction = undesirableAction;
    }
}
