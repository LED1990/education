package db.evaluation.criteria;

import model.evaluation.Evaluation;
import model.evaluation.EvaluationResults;
import model.evaluation.EvaluationSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface EvaluationCriteriaDao {

    Optional<List<Evaluation>> getAllEntities();
    Optional<EvaluationResults> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria);
}
