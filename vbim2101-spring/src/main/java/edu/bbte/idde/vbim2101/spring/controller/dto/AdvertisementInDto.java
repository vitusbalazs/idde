package edu.bbte.idde.vbim2101.spring.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AdvertisementInDto {
    @NotNull
    @Length(max = 50)
    private String title;
    @NotNull
    @Length(max = 50)
    private String address;
    @NotNull
    @Positive
    private Integer price;
    @NotNull
    @Positive
    private Integer surfaceArea;
    @NotNull
    @Positive
    private Integer rooms;
    @NotNull
    @Positive
    private Long owner;
}
