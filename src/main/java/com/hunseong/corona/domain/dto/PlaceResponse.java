package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.constant.PlaceType;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceResponse {

    private String placeName;
    private PlaceType placeType;
    private Integer capacity;

    public static PlaceResponse fromEntity(Place place) {
        return new PlaceResponse(place.getPlaceName(), place.getPlaceType(), place.getCapacity());
    }
}
