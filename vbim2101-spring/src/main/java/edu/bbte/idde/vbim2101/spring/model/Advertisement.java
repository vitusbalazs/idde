package edu.bbte.idde.vbim2101.spring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Advertisement extends BaseEntity {
    private String title;
    private String address;
    private Integer price;
    private Integer surfaceArea;
    private Integer rooms;
    private Long owner;
}
