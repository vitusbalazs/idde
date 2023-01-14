package edu.bbte.idde.vbim2101.spring.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ownerjpa")
public class Owner extends BaseEntity {
    private String name;
    private String email;
    private Integer age;
}
