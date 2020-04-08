package model.evaluation.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UndesirableActionForm {
    private Long id;
    private String description;
    private List<ClassificationForm> classifications;
    private List<IndicationForm> indications;

}
