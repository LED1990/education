package model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationSearchCriteria implements Serializable{

    //Evaluation
    private String comment;
    private String CaseNarrative;

    //UndesirableAction
    private String description;

    //Classification
    private String code;
    private String name;

    //Indication
    private String medicineName;
    private String info;

    //pagination + defaults
    int firstResult = 0;
    int maxResults = 5;

}