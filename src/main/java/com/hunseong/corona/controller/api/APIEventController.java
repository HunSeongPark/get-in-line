package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.APIDataResponse;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final EventService eventService;

    @GetMapping
    public APIDataResponse<List<EventDto>> events(Model model) {
        return APIDataResponse.of(eventService.getEvents());
    }
}
