package com.hunseong.corona.service;

import com.hunseong.corona.domain.dto.PlaceDto;
import com.hunseong.corona.repository.PlaceRepository;
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
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional(readOnly = true)
    public List<PlaceDto> getPlaces() {
        return placeRepository.findAll()
                .stream().map(PlaceDto::of).collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public EventDto getEvent(Long eventId) {
//
//        Event event = eventRepository.findFetchJoinPlaceById(eventId).orElseThrow(() -> {
//            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR);
//        });
//
//        return EventDto.of(event);
//    }
//
//    public EventResponse createEvent(EventCreateRequest eventCreateRequest) {
//
//        Place place = placeRepository.findById(eventCreateRequest.getPlaceId())
//                .orElseThrow(() -> {
//                    throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR);
//                });
//
//        return EventResponse.fromEntity(eventRepository.save(eventCreateRequest.toEntity(place)));
//    }
//
//    public EventResponse modifyEvent(Long eventId, EventEditRequest eventEditRequest) {
//
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> {
//                    throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR);
//                });
//
//        event.update(eventEditRequest);
//
//        return EventResponse.fromEntity(event);
//    }
//
//    public void deleteEvent(Long eventId) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> {
//                    throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR);
//                });
//        eventRepository.delete(event);
//    }
}
