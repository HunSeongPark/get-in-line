package com.hunseong.corona.repository;

import com.hunseong.corona.domain.Event;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.hunseong.corona.domain.QEvent.event;
import static com.hunseong.corona.domain.QPlace.place;

/**
 * Created by Hunseong on 2022/05/02
 */
@Repository
public class EventQueryRepository {

    private final JPAQueryFactory queryFactory;

    public EventQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Event> findAllOrderByEventStartTimeAsc() {
        return queryFactory
                .selectFrom(event)
                .join(event.place, place)
                .fetchJoin()
                .orderBy(event.eventStartDatetime.asc())
                .fetch();
    }
}
