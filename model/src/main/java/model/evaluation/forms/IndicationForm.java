package model.evaluation.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IndicationForm {

    private Long id;
    private String medicineName;
    private String info;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndicationForm that = (IndicationForm) o;

        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 31;//because DB is setting ID during flush, don't worry if collections are not too big
    }
}
