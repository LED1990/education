package app.evaluation.services.interfaces;

import model.evaluation.Evaluation;
import model.evaluation.EvaluationForm;

import java.util.List;
import java.util.Optional;

public interface EvaluationService {

    Long updateEvaluation(Evaluation evaluation);
    Optional<List<Evaluation>> getAll();
    Optional<EvaluationForm> getFormById(Long id);
}
