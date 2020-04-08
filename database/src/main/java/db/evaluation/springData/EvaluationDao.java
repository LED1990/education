package db.evaluation.springData;

import db.evaluation.criteria.EvaluationCriteriaDao;
import model.evaluation.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationDao extends JpaRepository<Evaluation, Long>, EvaluationCriteriaDao, EvaluationRepository {
}
