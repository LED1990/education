package model.evaluation.forms;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClassificationForm {

    private Long id;
    private String code;
    private String name;

}
