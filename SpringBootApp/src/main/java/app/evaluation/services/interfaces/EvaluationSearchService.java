package app.evaluation.services.interfaces;

import model.evaluation.Evaluation;
import model.evaluation.EvaluationSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EvaluationSearchService {

    Optional<Page<Evaluation>> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria, Integer page);
}
