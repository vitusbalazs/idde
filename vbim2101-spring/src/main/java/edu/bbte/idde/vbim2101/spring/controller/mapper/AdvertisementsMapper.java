package edu.bbte.idde.vbim2101.spring.controller.mapper;

import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementOutDto;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class AdvertisementsMapper {
    @Mapping(target = "owner", ignore = true)
    public abstract Advertisement advertisementFromDto(AdvertisementInDto advertisementDto);

    public abstract AdvertisementOutDto dtoFromAdvertisement(Advertisement advertisement);

    @IterableMapping(elementTargetType = AdvertisementOutDto.class)
    public abstract Collection<AdvertisementOutDto> dtosFromAdvertisements(Collection<Advertisement> advertisements);
}
