package model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Indication that = (Indication) o;

        return id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;//because DB is setting ID during flush, don't worry if collections are not too big
    }
}
