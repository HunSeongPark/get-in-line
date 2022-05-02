package com.hunseong.corona.domain.dto;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
public class EventEditRequest {

    private String eventName;

    private EventStatus eventStatus;

    private LocalDateTime eventStartDatetime;

    private LocalDateTime eventEndDatetime;

    @PositiveOrZero
    private Integer currentNumberOfPeople;

    @Positive
    private Integer capacity;

    private String memo;
}
