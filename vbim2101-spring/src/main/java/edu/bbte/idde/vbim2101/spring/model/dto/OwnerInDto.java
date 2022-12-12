package edu.bbte.idde.vbim2101.spring.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class OwnerInDto {
    @NotNull
    @Length(max=50)
    private String name;
    @NotNull
    @Length(max=50)
    private String email;
    @NotNull
    @Positive
    private Integer age;
}
