package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.APIDataResponse;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.domain.dto.PlaceDto;
import com.hunseong.corona.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Hunseong on 2022/05/02
 */
@RequiredArgsConstructor
@RequestMapping("/places")
@RestController
public class APIPlaceController {

    private final PlaceService placeService;

    @GetMapping
    public APIDataResponse<List<PlaceDto>> places() {
        return APIDataResponse.of(placeService.getPlaces());
    }
}
