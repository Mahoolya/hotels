package com.app.hotels.web.mapper;

import com.app.hotels.domain.Booking;
import com.app.hotels.web.dto.BookingDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDto toDto(Booking booking);

    Booking toEntity(BookingDto bookingDto);

    List<BookingDto> toDto(List<Booking> bookings);

}
