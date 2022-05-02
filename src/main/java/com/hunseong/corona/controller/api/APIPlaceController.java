package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.APIDataResponse;
import com.hunseong.corona.domain.dto.EventDto;
import com.hunseong.corona.domain.dto.PlaceDto;
import com.hunseong.corona.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // TODO
//    /places/{place-id}	GET	    장소 세부 정보 조회
//    /places	            POST	장소 등록
//    /places/{place-id}	PUT	    장소 정보 변경
//    /places/{place-id}	DELETE	장소 삭제

    @GetMapping
    public APIDataResponse<List<PlaceDto>> places() {
        return APIDataResponse.of(placeService.getPlaces());
    }

    @GetMapping("/{placeId}")
    public APIDataResponse<PlaceDto> getPlace(@PathVariable Long placeId) {
        return APIDataResponse.of(placeService.getPlace(placeId));
    }
}
