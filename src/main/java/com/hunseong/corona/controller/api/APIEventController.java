package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.*;
import com.hunseong.corona.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Hunseong on 2022/05/02
 */
@RequiredArgsConstructor
@RequestMapping("/events")
@RestController
public class APIEventController {

    // TODO
//    /api/events/{event-id}	DELETE	이벤트 삭제

    private final EventService eventService;

    @GetMapping
    public APIDataResponse<List<EventDto>> events() {
        return APIDataResponse.of(eventService.getEvents());
    }

    @GetMapping("/{eventId}")
    public APIDataResponse<EventDto> getEvent(@PathVariable Long eventId) {
        return APIDataResponse.of(eventService.getEvent(eventId));
    }

    @PostMapping
    public APIDataResponse<EventResponse> createEvent(@Valid @RequestBody EventCreateRequest eventCreateRequest) {
        return APIDataResponse.of(eventService.createEvent(eventCreateRequest));
    }

    @PutMapping("/{eventId}")
    public APIDataResponse<EventResponse> modifyEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody EventEditRequest eventEditRequest) {
        return APIDataResponse.of(eventService.modifyEvent(eventId, eventEditRequest));
    }

    @DeleteMapping("/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return "SUCCESS";
    }
}
