package db.evaluation.criteria;

import model.evaluation.Evaluation;

import java.util.List;

public interface EvaluationCriteriaDao {

    List<Evaluation> getAllEntities();
}
