package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.PlaceType;
import com.hunseong.corona.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceDto {

    private Long id;
    private PlaceType placeType;
    private String placeName;
    private String address;
    private String phoneNumber;
    private Integer capacity;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static PlaceDto of(Long id, PlaceType placeType, String placeName,
                              String address, String phoneNumber, Integer capacity,
                              String memo, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new PlaceDto(id, placeType, placeName, address, phoneNumber,
                capacity, memo, createdAt, modifiedAt);
    }

    public static PlaceDto of(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getPlaceType(),
                place.getPlaceName(),
                place.getAddress(),
                place.getPhoneNumber(),
                place.getCapacity(),
                place.getMemo(),
                place.getCreatedAt(),
                place.getModifiedAt());
    }

}
