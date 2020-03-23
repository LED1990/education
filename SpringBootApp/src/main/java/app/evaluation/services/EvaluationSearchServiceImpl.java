package app.evaluation.services;

import app.evaluation.services.interfaces.EvaluationSearchService;
import db.evaluation.springData.EvaluationDao;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationResults;
import model.evaluation.EvaluationSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationSearchServiceImpl implements EvaluationSearchService {

    private EvaluationDao evaluationDao;

    @Autowired
    public EvaluationSearchServiceImpl(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    public Optional<Page<Evaluation>> getByCriteria(EvaluationSearchCriteria evaluationSearchCriteria, Integer pageNumber) {
        Optional<EvaluationResults> result;
        Page<Evaluation> page;
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        evaluationSearchCriteria.setFirstResult(pageNumber);
        result = evaluationDao.getByCriteria(evaluationSearchCriteria);
        if (result.isPresent()) {
            page = new PageImpl<>(result.get().getResults(), PageRequest.of(pageNumber, 5), result.get().getTotalResults());//always max 5 results on page
            return Optional.of(page);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Evaluation>> getAll() {
        return evaluationDao.getAllEntities();
    }
}
