package com.hunseong.corona.service;

import com.hunseong.corona.constant.ErrorCode;
import com.hunseong.corona.domain.Event;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.exception.GeneralException;
import com.hunseong.corona.repository.EventQueryRepository;
import com.hunseong.corona.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hunseong on 2022/05/02
 */
@RequiredArgsConstructor
@Transactional
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;

    @Transactional(readOnly = true)
    public List<EventDto> getEvents() {
        return eventQueryRepository.findAllOrderByEventStartTimeAsc()
                .stream().map(EventDto::of).collect(Collectors.toList());
    }

    public EventDto getEvent(Long eventId) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR);
        });

        return EventDto.of(event);
    }
}
