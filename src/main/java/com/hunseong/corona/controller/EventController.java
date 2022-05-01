package com.hunseong.corona.controller;

import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Hunseong on 2022/05/02
 */
@RequiredArgsConstructor
@RequestMapping("/events")
@Controller
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String events(Model model) {
        List<EventDto> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "test";
    }
}
