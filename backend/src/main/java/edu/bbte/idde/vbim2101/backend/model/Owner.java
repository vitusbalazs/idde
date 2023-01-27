package edu.bbte.idde.vbim2101.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Owner extends BaseEntity {
    private String name;
    private String email;
    private Integer age; // 1-5
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer version;

    public Owner(String name, String email, Integer age, Integer version) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
        this.version = version;
    }
}
