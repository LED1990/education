package model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Computer {


    @Id
    private String _id;
    private String name;

    private List<Interfaces> interfaces;

    public Computer(String name, List<Interfaces> interfaces) {
        this.name = name;
        this.interfaces = interfaces;
    }
}
