package com.hunseong.corona.repository;

import com.hunseong.corona.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hunseong on 2022/05/02
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
