package com.hunseong.corona.service;

import com.hunseong.corona.constant.ErrorCode;
import com.hunseong.corona.domain.Place;
import com.hunseong.corona.domain.dto.PlaceCreateRequest;
import com.hunseong.corona.domain.dto.PlaceDto;
import com.hunseong.corona.domain.dto.PlaceResponse;
import com.hunseong.corona.exception.GeneralException;
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

    @Transactional(readOnly = true)
    public PlaceDto getPlace(Long placeId) {

        Place place = placeRepository.findById(placeId).orElseThrow(() -> {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR);
        });

        return PlaceDto.of(place);
    }

    public PlaceResponse createPlace(PlaceCreateRequest placeCreateRequest) {
        return PlaceResponse.fromEntity(placeRepository.save(placeCreateRequest.toEntity()));
    }

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
