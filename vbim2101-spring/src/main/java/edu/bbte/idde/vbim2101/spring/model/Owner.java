package edu.bbte.idde.vbim2101.spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "owner")
    private Collection<Advertisement> advertisements;
}
