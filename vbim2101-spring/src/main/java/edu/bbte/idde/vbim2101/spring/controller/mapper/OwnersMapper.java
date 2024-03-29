package edu.bbte.idde.vbim2101.spring.controller.mapper;

import edu.bbte.idde.vbim2101.spring.controller.dto.OwnerInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.OwnerOutDto;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class OwnersMapper {
    public abstract Owner ownerFromDto(OwnerInDto ownerDto);

    public abstract OwnerOutDto dtoFromOwner(Owner owner);

    @IterableMapping(elementTargetType = OwnerOutDto.class)
    public abstract Collection<OwnerOutDto> dtosFromOwners(Collection<Owner> owners);
}
