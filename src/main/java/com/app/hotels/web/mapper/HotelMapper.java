package com.app.hotels.web.mapper;

import com.app.hotels.domain.Hotel;
import com.app.hotels.web.dto.HotelDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDto toDto(Hotel hotel);

    List<HotelDto> toDto(List<Hotel> hotels);

    Hotel toEntity(HotelDto hotelDto);

}
