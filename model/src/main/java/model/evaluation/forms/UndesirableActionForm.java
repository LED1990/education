package model.evaluation.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UndesirableActionForm {
    private Long id;
    private String description;
    private List<ClassificationForm> classifications = new ArrayList<>();
    private List<IndicationForm> indications = new ArrayList<>();

    private Evaluation evaluation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UndesirableActionForm that = (UndesirableActionForm) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;//because DB is setting ID during flush, don't worry if collections are not too big
    }
}
