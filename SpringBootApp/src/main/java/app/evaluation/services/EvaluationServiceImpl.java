package app.evaluation.services;

import app.evaluation.services.interfaces.EvaluationService;
import app.restmvc.mappers.EvaluationToFormMapper;
import app.restmvc.mappers.FormToEvaluationMapper;
import db.evaluation.springData.EvaluationDao;
import lombok.extern.slf4j.Slf4j;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private EvaluationDao evaluationDao;
    private EvaluationToFormMapper evaluationToFormMapper;
    private FormToEvaluationMapper formToEvaluationMapper;

    @Autowired
    public EvaluationServiceImpl(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
        this.evaluationToFormMapper = EvaluationToFormMapper.INSTANCE;
        this.formToEvaluationMapper = FormToEvaluationMapper.INSTANCE;
    }

    @Override
    public Long updateEvaluation(Evaluation evaluation) {

        return evaluationDao.updateEvaluation(evaluation);
    }

    @Override
    public Optional<List<Evaluation>> getAll() {
        return evaluationDao.getAllEntities();
    }

    @Override
    public Optional<EvaluationForm> getFormById(Long id) {
        Optional<Evaluation> result = evaluationDao.findById(id);
        if (!result.isPresent()) {
            throw new RuntimeException();//todo exception handling
        }

        return Optional.ofNullable(evaluationToFormMapper.evaluationToForm(result.get()));
    }
}
