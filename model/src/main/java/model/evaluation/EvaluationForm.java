package model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EvaluationForm {

    private Long id;
    private String comment;
    private String caseNarrative;
    private List<UndesirableActionForm> undesirableActions;

}
