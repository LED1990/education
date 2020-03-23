package model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EvaluationResults {
    private Long totalResults;
    private List<Evaluation> results;
}
