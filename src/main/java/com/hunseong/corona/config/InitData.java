package com.hunseong.corona.config;

import com.hunseong.corona.constant.EventStatus;
import com.hunseong.corona.constant.PlaceType;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

/**
 * Created by Hunseong on 2022/05/02
 */
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    private void init() {
        initService.init();
    }

    @Component
    static class InitService {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {

            Place place1 = Place.of(PlaceType.COMMON,
                    "커먼 플레이스",
                    "경기도 옹이옹",
                    "010-1111-1111",
                    10,
                    "memo1");

            Place place2 = Place.of(
                    PlaceType.PARTY,
                    "파티룸룸",
                    "서울특별시 오아옹",
                    "010-2222-2222",
                    200,
                    "memo2");

            Place place3 = Place.of(PlaceType.RESTAURANT,
                    "스파게티 먹고싶다",
                    "경상북도 에오앵",
                    "010-3333-3333",
                    30,
                    "memo3");

            Place place4 = Place.of(
                    PlaceType.SPORTS,
                    "근육맨 체육관",
                    "부산광역시 오앵옹",
                    "010-4444-4444",
                    100,
                    "memo4");


            em.persist(place1);
            em.persist(place2);
            em.persist(place3);
            em.persist(place4);

            Event event1 = Event.of(
                    place1,
                    "제일 늦은 이벤트",
                    EventStatus.OPENED,
                    LocalDateTime.of(2021, 3, 1, 10, 10),
                    LocalDateTime.of(2021, 3, 2, 10, 10),
                    0,
                    10,
                    "memo1"
            );

            Event event2 = Event.of(
                    place2,
                    "제일 늦은 이벤트보단 빠른 이벤트",
                    EventStatus.OPENED,
                    LocalDateTime.of(2021, 2, 1, 10, 10),
                    LocalDateTime.of(2021, 2, 2, 10, 10),
                    0,
                    37,
                    "memo2"
            );

            Event event3 = Event.of(
                    place3,
                    "10분 차이로 더 빠른 이벤트",
                    EventStatus.OPENED,
                    LocalDateTime.of(2021, 2, 1, 10, 0),
                    LocalDateTime.of(2021, 3, 2, 10, 10),
                    5,
                    30,
                    "memo3"
            );

            Event event4 = Event.of(
                    place4,
                    "세상에 2020년 이벤트가 아직도!?",
                    EventStatus.OPENED,
                    LocalDateTime.of(2020, 11, 1, 10, 10),
                    LocalDateTime.of(2020, 11, 2, 10, 10),
                    44,
                    100,
                    "memo4"
            );

            em.persist(event1);
            em.persist(event2);
            em.persist(event3);
            em.persist(event4);
        }
    }
}
