package app.restmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private String provider;
    private Integer accNumber;

    public String getProvider() {
        return provider;
    }
}
