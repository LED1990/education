package app.restmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private String provider;
    private Integer accNumber;
    private Set<ContactDto> contacts;

    public String getProvider() {
        return provider;
    }
}
