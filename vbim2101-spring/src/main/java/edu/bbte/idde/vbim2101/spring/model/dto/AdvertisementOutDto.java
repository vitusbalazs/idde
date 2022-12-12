package edu.bbte.idde.vbim2101.spring.model.dto;

import lombok.Data;

@Data
public class AdvertisementOutDto {
    private Long id;
    private String title;
    private String address;
    private Integer price;
    private Integer surfaceArea;
    private Integer rooms;
    private Long owner;
}
