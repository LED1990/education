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
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classification that = (Classification) o;

        return id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;//because DB is setting ID during flush, don't worry if collections are not too big
    }
}
