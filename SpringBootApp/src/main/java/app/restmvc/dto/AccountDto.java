package app.restmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto implements Serializable {
    private Long id;
    private String provider;
    private Integer accNumber;

}
