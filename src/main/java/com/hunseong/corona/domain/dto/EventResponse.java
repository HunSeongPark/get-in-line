package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.domain.Event;
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
public class EventResponse {

    private String placeName;
    private String eventName;
    private EventStatus eventStatus;

    public static EventResponse fromEntity(Event event) {
        return new EventResponse(event.getPlace().getPlaceName(), event.getEventName(), event.getEventStatus());
    }
}
