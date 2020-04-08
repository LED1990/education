package app.restmvc.mappers;

import model.evaluation.UndesirableAction;
import model.evaluation.forms.UndesirableActionForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UndesirableActionToFormMapper {

    UndesirableActionToFormMapper INSTANE = Mappers.getMapper(UndesirableActionToFormMapper.class);

    UndesirableActionForm undesirableActionToForm(UndesirableAction undesirableAction);
}
