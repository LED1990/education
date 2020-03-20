package app.evaluation.services.interfaces;

import model.evaluation.Evaluation;
import model.evaluation.EvaluationSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface EvaluationSearchService {

    Optional<List<Evaluation>> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria);
    Optional<List<Evaluation>> getAll();
}
