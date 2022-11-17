package edu.bbte.idde.vbim2101.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Owner extends BaseEntity {
    private String name;
    private String email;
    private Integer age; // 1-5

    public Owner(String name, String email, Integer age) {
        super();
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
