package edu.bbte.idde.vbim2101.spring.controller.dto;

import edu.bbte.idde.vbim2101.spring.model.Owner;
import lombok.Data;

@Data
public class AdvertisementOutDto {
    private Long id;
    private String title;
    private String address;
    private Integer price;
    private Integer surfaceArea;
    private Integer rooms;
    private OwnerOutDto owner;
}
