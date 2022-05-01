package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.constant.PlaceType;
import com.hunseong.corona.domain.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@AllArgsConstructor
public class EventDto {

    private Long id;
    private PlaceDto placeDto;
    private String eventName;
    private EventStatus eventStatus;
    private LocalDateTime eventStartDatetime;
    private LocalDateTime eventEndDatetime;
    private Integer currentNumberOfPeople;
    private Integer capacity;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static EventDto of(Long id, PlaceDto placeDto, String eventName,
                              EventStatus eventStatus, LocalDateTime eventStartDatetime,
                              LocalDateTime eventEndDatetime, Integer currentNumberOfPeople,
                              Integer capacity, String memo, LocalDateTime createdAt,
                              LocalDateTime modifiedAt) {

        return new EventDto(id, placeDto, eventName, eventStatus, eventStartDatetime,
                eventEndDatetime, currentNumberOfPeople, capacity, memo, createdAt, modifiedAt);
    }

    public static EventDto of(Event event) {

        return new EventDto(
                event.getId(),
                PlaceDto.of(event.getPlace()),
                event.getEventName(),
                event.getEventStatus(),
                event.getEventStartDatetime(),
                event.getEventEndDatetime(),
                event.getCurrentNumberOfPeople(),
                event.getCapacity(),
                event.getMemo(),
                event.getCreatedAt(),
                event.getModifiedAt());
    }
}
