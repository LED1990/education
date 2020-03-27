package model.restmvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer numberOfRewords;
    private String categoryCode;

    public Category(String name, Integer numberOfRewords, String categoryCode) {
        this.name = name;
        this.numberOfRewords = numberOfRewords;
        this.categoryCode = categoryCode;
    }
}
