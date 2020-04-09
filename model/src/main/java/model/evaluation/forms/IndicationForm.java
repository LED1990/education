package model.evaluation.forms;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IndicationForm {

    private Long id;
    private String medicineName;
    private String info;

}
