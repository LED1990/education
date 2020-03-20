package db.evaluation.criteria;


import model.evaluation.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EvaluationCriteriaDaoImpl implements EvaluationCriteriaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Evaluation>> getAllEntities() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evaluation> criteriaQuery = criteriaBuilder.createQuery(Evaluation.class);
        Root<Evaluation> root = criteriaQuery.from(Evaluation.class);
        criteriaQuery.select(root);

        TypedQuery<Evaluation> query = entityManager.createQuery(criteriaQuery);
        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<List<Evaluation>> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evaluation> criteriaQuery = criteriaBuilder.createQuery(Evaluation.class);
        Root<Evaluation> root = criteriaQuery.from(Evaluation.class);

        List<Predicate> predicates = new ArrayList<>();

        SetJoin<Evaluation, UndesirableAction> undesirableActionJoin = null;
        SetJoin<UndesirableAction, Classification> classificationJoin;
        SetJoin<UndesirableAction, Indication> indicationJoin;

        if (!StringUtils.isEmpty(evaluationSearchCriteria.getComment()) || !StringUtils.isEmpty(evaluationSearchCriteria.getCaseNarrative())) {
            if (!StringUtils.isEmpty(evaluationSearchCriteria.getCaseNarrative())) {
                predicates.add(criteriaBuilder.like(root.get(Evaluation_.CASE_NARRATIVE), evaluationSearchCriteria.getCaseNarrative()));
            }
            if (!StringUtils.isEmpty(evaluationSearchCriteria.getComment())) {
                predicates.add(criteriaBuilder.like(root.get(Evaluation_.COMMENT), evaluationSearchCriteria.getComment()));
            }
        }
        if (!StringUtils.isEmpty(evaluationSearchCriteria.getDescription())) {
            undesirableActionJoin = root.join(Evaluation_.undesirableActions, JoinType.LEFT);
            predicates.add(criteriaBuilder.like(undesirableActionJoin.get(UndesirableAction_.DESCRIPTION), evaluationSearchCriteria.getDescription()));
        }
        if (!StringUtils.isEmpty(evaluationSearchCriteria.getCode()) || !StringUtils.isEmpty(evaluationSearchCriteria.getName())) {
            if (undesirableActionJoin == null) {
                undesirableActionJoin = root.join(Evaluation_.undesirableActions, JoinType.LEFT);
            }
            classificationJoin = undesirableActionJoin.joinSet(UndesirableAction_.CLASSIFICATIONS, JoinType.LEFT);
            if (!StringUtils.isEmpty(evaluationSearchCriteria.getCode())) {
                predicates.add(criteriaBuilder.like(classificationJoin.get(Classification_.CODE), evaluationSearchCriteria.getCode()));
            }
            if (!StringUtils.isEmpty(evaluationSearchCriteria.getName())) {
                predicates.add(criteriaBuilder.like(classificationJoin.get(Classification_.NAME), evaluationSearchCriteria.getName()));
            }
        }
        if (!StringUtils.isEmpty(evaluationSearchCriteria.getMedicineName()) || !StringUtils.isEmpty(evaluationSearchCriteria.getInfo())) {
            if (undesirableActionJoin == null) {
                undesirableActionJoin = root.join(Evaluation_.undesirableActions, JoinType.LEFT);
            }
            indicationJoin = undesirableActionJoin.joinSet(UndesirableAction_.INDICATIONS, JoinType.LEFT);
            if (!StringUtils.isEmpty(evaluationSearchCriteria.getMedicineName())) {
                predicates.add(criteriaBuilder.like(indicationJoin.get(Indication_.MEDICINE_NAME), evaluationSearchCriteria.getMedicineName()));
            }
            if (!StringUtils.isEmpty(evaluationSearchCriteria.getInfo())) {
                predicates.add(criteriaBuilder.like(indicationJoin.get(Indication_.INFO), evaluationSearchCriteria.getInfo()));
            }
        }
        Predicate finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[]{}));
        criteriaQuery.select(root).where(finalPredicate).distinct(true);
        TypedQuery<Evaluation> query = entityManager.createQuery(criteriaQuery);
        return  Optional.ofNullable(query.getResultList());
    }
}
