package model.evaluation.forms;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UndesirableActionForm {
    private Long id;
    private String description;
    private List<ClassificationForm> classifications;
    private List<IndicationForm> indications;

}
