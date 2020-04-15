package app.restmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable{
    private Long id;
    private String userName;
    private String userLastName;
    private Set<AccountDto> accounts;

}
