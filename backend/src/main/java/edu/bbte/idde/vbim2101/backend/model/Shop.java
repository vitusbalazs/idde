package edu.bbte.idde.vbim2101.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Shop extends BaseEntity {
    private String name;
    private String address;
    private Integer rating; // 1-5

    public Shop(String name, String address, Integer rating) {
        super();
        this.name = name;
        this.address = address;
        this.rating = rating;
    }
}
