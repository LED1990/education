package db.evaluation.criteria;

import model.evaluation.Evaluation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EvaluationCriteriaDaoImpl implements EvaluationCriteriaDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Evaluation> getAllEntities() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evaluation> criteriaQuery = criteriaBuilder.createQuery(Evaluation.class);
        Root<Evaluation> root = criteriaQuery.from(Evaluation.class);
        criteriaQuery.select(root);

        TypedQuery<Evaluation> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
