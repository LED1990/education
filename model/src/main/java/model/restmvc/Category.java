package model.restmvc;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
public class Category {
    private Long id;
    private String name;
    private Integer numberOfRewords;
    private String categoryCode;
}
