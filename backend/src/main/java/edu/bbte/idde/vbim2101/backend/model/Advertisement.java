package edu.bbte.idde.vbim2101.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Advertisement extends BaseEntity {
    private String title;
    private String address;
    private Integer price;
    private Integer surfaceArea;
    private Integer rooms;
    private Long owner;

    public Advertisement(String title, String address, Integer price, Integer surfaceArea, Integer rooms, Long owner) {
        super();
        this.title = title;
        this.address = address;
        this.price = price;
        this.surfaceArea = surfaceArea;
        this.rooms = rooms;
        this.owner = owner;
    }
}
