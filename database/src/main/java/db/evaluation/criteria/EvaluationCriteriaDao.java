package db.evaluation.criteria;

import model.evaluation.Evaluation;
import model.evaluation.EvaluationSearchCriteria;

import java.util.List;

public interface EvaluationCriteriaDao {

    List<Evaluation> getAllEntities();
    List<Evaluation> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria);
}
