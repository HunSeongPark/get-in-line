package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.*;
import com.hunseong.corona.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{placeId}")
    public APIDataResponse<PlaceDto> getPlace(@PathVariable Long placeId) {
        return APIDataResponse.of(placeService.getPlace(placeId));
    }

    @PostMapping
    public APIDataResponse<PlaceResponse> createPlace(@Valid @RequestBody PlaceCreateRequest request) {
        return APIDataResponse.of(placeService.createPlace(request));
    }

    @PutMapping("/{placeId}")
    public APIDataResponse<PlaceResponse> modifyPlace(
            @PathVariable Long placeId,
            @Valid @RequestBody PlaceEditRequest request) {

        return APIDataResponse.of(placeService.modifyPlace(placeId, request));
    }

    @DeleteMapping("/{placeId}")
    public String deletePlace(@PathVariable Long placeId) {
        placeService.deletePlace(placeId);
        return "SUCCESS";
    }
}
