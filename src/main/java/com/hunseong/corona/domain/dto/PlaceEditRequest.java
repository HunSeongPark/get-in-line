package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.PlaceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceEditRequest {

    private String placeName;

    private PlaceType placeType;

    private String address;

    private String phoneNumber;

    @PositiveOrZero
    private Integer capacity;

    private String memo;
}
