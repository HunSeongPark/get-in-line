package com.hunseong.corona.service;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.constant.PlaceType;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.Place;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.domain.dto.PlaceDto;
import com.hunseong.corona.exception.GeneralException;
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
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hunseong on 2022/05/02
 */
@SpringBootTest
@Transactional
class PlaceServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    PlaceService placeService;

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

    @DisplayName("[GET /places] 모든 장소를 받아온다")
    @Test
    void getAllPlaces() {

        // given

        // when
        List<PlaceDto> result = placeService.getPlaces();

        for (PlaceDto placeDto : result) {
            System.out.println("place = " + placeDto.getPlaceName());
        }

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).extracting("placeName").isEqualTo("p1");
        assertThat(result.get(1)).extracting("placeName").isEqualTo("p2");
    }

    @DisplayName("[GET /places/{placeId}] placeId에 해당하는 장소 받아오기 - 성공")
    @Test
    void getPlace() {

        // given


        // when
        PlaceDto placeDto1 = placeService.getPlace(1L);
        PlaceDto placeDto2 = placeService.getPlace(2L);

        // then
        assertThat(placeDto1.getPlaceName()).isEqualTo("p1");
        assertThat(placeDto2.getPlaceName()).isEqualTo("p2");
    }

    @DisplayName("[GET /places/{placeId}] placeId에 해당하는 장소 받아오기 - place 존재 X")
    @Test
    void getPlace_NotExistPlace() {

        // given

        // when & then
        assertThrows(GeneralException.class,
                () -> placeService.getPlace(0L));
    }
}