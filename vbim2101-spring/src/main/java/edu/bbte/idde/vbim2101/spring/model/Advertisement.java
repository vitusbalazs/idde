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
@Table(name = "advertisementjpa")
public class Advertisement extends BaseEntity {
    private String title;
    private String address;
    private Integer price;
    private Integer surfaceArea;
    private Integer rooms;
    private Long owner;
}
