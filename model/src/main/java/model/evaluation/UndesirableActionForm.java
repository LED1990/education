package model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UndesirableActionForm {
    private Long id;
    private String description;
    private List<Classification> classifications = new ArrayList<>();
    private List<Indication> indications = new ArrayList<>();
}
