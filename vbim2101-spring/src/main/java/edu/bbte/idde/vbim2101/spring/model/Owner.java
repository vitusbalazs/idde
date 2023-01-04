package edu.bbte.idde.vbim2101.spring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Owner extends BaseEntity {
    private String name;
    private String email;
    private Integer age;
}
