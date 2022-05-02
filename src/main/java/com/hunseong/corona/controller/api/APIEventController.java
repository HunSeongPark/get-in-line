package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.APIDataResponse;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Hunseong on 2022/05/02
 */
@RequiredArgsConstructor
@RequestMapping("/events")
@RestController
public class APIEventController {

    // TODO
//    /api/events/{event-id}	GET	    이벤트 세부 정보 조회
//    /api/events	            POST	이벤트 등록
//    /api/events/{event-id}	PUT	    이벤트 정보 변경
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
}
