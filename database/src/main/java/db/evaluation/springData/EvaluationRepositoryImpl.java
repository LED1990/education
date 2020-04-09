package db.evaluation.springData;

import lombok.extern.slf4j.Slf4j;
import model.evaluation.Evaluation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
public class EvaluationRepositoryImpl implements EvaluationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Long updateEvaluation(Evaluation evaluation) {
        log.debug("Updating entity with JPA");
        Evaluation result = entityManager.merge(evaluation);
        return result.getId();
    }
}
