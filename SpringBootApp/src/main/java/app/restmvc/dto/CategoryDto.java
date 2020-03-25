package app.restmvc.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private Integer numberOfRewords;
    private String code;
}
