package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.Place;
import com.hunseong.corona.service.EventService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventRequest {

    @NotNull
    private Long placeId;

    @NotBlank
    private String eventName;

    @NotNull
    private EventStatus eventStatus;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime eventStartDatetime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime eventEndDatetime;

    @NotNull
    @PositiveOrZero
    private Integer currentNumberOfPeople;

    @NotNull
    @Positive
    private Integer capacity;

    private String memo;

    public Event toEntity(Place place) {
        return Event.of(place, eventName, eventStatus,
                eventStartDatetime, eventEndDatetime, currentNumberOfPeople, capacity, memo);
    }
}
