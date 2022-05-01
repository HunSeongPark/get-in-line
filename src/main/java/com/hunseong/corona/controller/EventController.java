package com.hunseong.corona.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "event/index";
    }
}
