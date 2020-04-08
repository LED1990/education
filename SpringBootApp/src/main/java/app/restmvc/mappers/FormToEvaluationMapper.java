package app.restmvc.mappers;

import model.evaluation.Evaluation;
import model.evaluation.forms.EvaluationForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FormToEvaluationMapper {

    FormToEvaluationMapper INSTANCE = Mappers.getMapper(FormToEvaluationMapper.class);

    Evaluation formToEvaluation(EvaluationForm evaluationForm);
}
