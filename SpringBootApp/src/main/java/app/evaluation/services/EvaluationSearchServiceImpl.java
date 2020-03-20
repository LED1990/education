package app.evaluation.services;

import app.evaluation.services.interfaces.EvaluationSearchService;
import db.evaluation.springData.EvaluationDao;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationSearchServiceImpl implements EvaluationSearchService{

    private EvaluationDao evaluationDao;

    @Autowired
    public EvaluationSearchServiceImpl(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    public Optional<List<Evaluation>> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria) {
        return evaluationDao.getByCriteria(evaluationSearchCriteria);
    }

    @Override
    public Optional<List<Evaluation>> getAll() {
        return evaluationDao.getAllEntities();
    }
}
