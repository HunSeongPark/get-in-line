package com.hunseong.corona.domain;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.domain.dto.EventEditRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'OPENED'")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Column(nullable = false, columnDefinition = "datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventStartDatetime;

    @Column(nullable = false, columnDefinition = "datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime eventEndDatetime;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer currentNumberOfPeople;

    @Column(nullable = false)
    private Integer capacity;

    private String memo;

    protected Event(Place place, String eventName, EventStatus eventStatus,
                 LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime,
                 Integer currentNumberOfPeople, Integer capacity, String memo) {
        if (place != null) {
            this.changePlace(place);
        }
        this.eventName = eventName;
        this.eventStatus = eventStatus;
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
        this.currentNumberOfPeople = currentNumberOfPeople;
        this.capacity = capacity;
        this.memo = memo;
    }

    public static Event of(Place place, String eventName, EventStatus eventStatus,
                           LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime,
                           Integer currentNumberOfPeople, Integer capacity, String memo) {

        return new Event(place, eventName, eventStatus,
                eventStartDatetime, eventEndDatetime, currentNumberOfPeople, capacity, memo);
    }

    // ????????? ???????????? ?????? ?????????
    private void changePlace(Place place) {
        this.place = place;
        place.getEvents().add(this);
    }

    public void update(EventEditRequest request) {
        if (hasText(request.getEventName())) {
            this.eventName = request.getEventName();
        }
        if (request.getEventStatus() != null) {
            this.eventStatus = request.getEventStatus();
        }
        if (request.getEventStartDatetime() != null) {
            this.eventStartDatetime = request.getEventStartDatetime();
        }
        if (request.getEventEndDatetime() != null) {
            this.eventEndDatetime = request.getEventEndDatetime();
        }
        if (request.getCurrentNumberOfPeople() != null) {
            this.currentNumberOfPeople = request.getCurrentNumberOfPeople();
        }
        if (request.getCapacity() != null) {
            this.capacity = request.getCapacity();
        }
        if (hasText(request.getMemo())) {
            this.memo = request.getMemo();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(eventName, event.eventName) && Objects.equals(eventStartDatetime, event.eventStartDatetime) && Objects.equals(eventEndDatetime, event.eventEndDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventStartDatetime, eventEndDatetime);
    }
}
