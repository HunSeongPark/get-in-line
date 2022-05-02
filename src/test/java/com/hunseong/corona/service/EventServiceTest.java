package com.hunseong.corona.service;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.constant.PlaceType;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.Place;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.domain.dto.EventEditRequest;
import com.hunseong.corona.exception.GeneralException;
import com.sun.jdi.request.EventRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Hunseong on 2022/05/02
 */
@SpringBootTest
@Transactional
class EventServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    EventService eventService;

    @BeforeEach
    public void before() {
        Place place1 = Place.of(
                PlaceType.COMMON,
                "p1",
                "address1",
                "010",
                10,
                "memo1");

        Place place2 = Place.of(
                PlaceType.COMMON,
                "p2",
                "address2",
                "1010",
                20,
                "memo2");

        em.persist(place1);
        em.persist(place2);

        Event event1 = Event.of(
                place1,
                "event1",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 3, 1, 10, 10),
                LocalDateTime.of(2021, 3, 2, 10, 10),
                0,
                10,
                "memo1"
        );

        Event event2 = Event.of(
                place2,
                "event2",
                EventStatus.OPENED,
                LocalDateTime.of(2021, 2, 1, 10, 10),
                LocalDateTime.of(2021, 2, 2, 10, 10),
                0,
                10,
                "memo2"
        );

        em.persist(event1);
        em.persist(event2);

        System.out.println("event1 = " + event1);
        System.out.println("event2 = " + event2);
    }

    @DisplayName("[GET /events] 모든 이벤트를 시작날짜가 빠른순으로 받아온다")
    @Test
    void getAllEvents() {

        // given

        // when
        List<EventDto> result = eventService.getEvents();

        for (EventDto eventDto : result) {
            System.out.println("place = " + eventDto.getPlaceDto().getPlaceName());
        }
        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).extracting("eventName").isEqualTo("event2");
        assertThat(result.get(1)).extracting("eventName").isEqualTo("event1");
    }

    @DisplayName("[GET /events/{eventId}] eventId에 해당하는 이벤트 받아오기 - 성공")
    @Test
    void getEvent() {

        // given


        // when
        EventDto eventDto1 = eventService.getEvent(11L);
        EventDto eventDto2 = eventService.getEvent(12L);

        // then
        assertThat(eventDto1.getEventName()).isEqualTo("event1");
        assertThat(eventDto2.getEventName()).isEqualTo("event2");
    }

    @DisplayName("[GET /events/{eventId}] eventId에 해당하는 이벤트 받아오기 - event 존재 X")
    @Test
    void getEvent_NotExistEvent() {

        // given

        // when & then
        assertThrows(GeneralException.class,
                () -> eventService.getEvent(0L));
    }

    @DisplayName("[PUT /events/{eventId}] eventId에 해당하는 event 정보 변경 - 성공")
    @Test
    void editEvent() {

        // given
        EventDto event = eventService.getEvents().get(0);

        EventEditRequest req = new EventEditRequest("change", null, null, null,
                null, null, null);
        // when
        eventService.modifyEvent(event.getId(), req);

        em.flush();
        em.clear();

        EventDto findEvent = eventService.getEvent(event.getId());

        // then
        assertThat(findEvent.getEventName()).isEqualTo(req.getEventName());
    }
}