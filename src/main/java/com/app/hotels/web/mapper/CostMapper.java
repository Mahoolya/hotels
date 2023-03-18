package com.app.hotels.web.mapper;

import com.app.hotels.domain.Cost;
import com.app.hotels.web.dto.CostDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CostMapper {

    CostDto toDto(Cost cost);

    Cost toEntity(CostDto costDto);

    List<CostDto> toDto(List<Cost> costs);

}
