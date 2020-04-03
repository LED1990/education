package app.restmvc.mappers;

import model.evaluation.Evaluation;
import model.evaluation.EvaluationForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EvaluationToFormMapper {

    EvaluationToFormMapper INSTANCE = Mappers.getMapper(EvaluationToFormMapper.class);

    EvaluationForm evaluationToForm(Evaluation evaluation);
}
