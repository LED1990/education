package model.jms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SimpleJmsMessage implements Serializable {

    @Transient
    static final long serialVersionUID = -9374623874327L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    private UUID id;
    private String message;


}
