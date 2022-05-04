package com.hunseong.corona.controller.api;

import com.hunseong.corona.domain.dto.APIDataResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hunseong on 2022/05/04
 */
@RestController
@RequestMapping("/api")
public class APIAuthController {

    @PostMapping("/login")
    public APIDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return APIDataResponse.empty();
    }
}
