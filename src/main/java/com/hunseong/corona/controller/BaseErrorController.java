package com.hunseong.corona.controller;

import com.hunseong.corona.constant.ErrorCode;
import com.hunseong.corona.domain.dto.APIErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hunseong on 2022/05/02
 */
@Controller
public class BaseErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<APIErrorResponse> error(HttpServletResponse response) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
        ErrorCode errorCode = ErrorCode.fromStatus(httpStatus);

        // 리디렉션이 아닌 url에 직접 /error를 입력하여 접속한 경우
        if (httpStatus == HttpStatus.OK) {
            httpStatus = HttpStatus.FORBIDDEN;
            errorCode = ErrorCode.BAD_REQUEST;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(APIErrorResponse.of(false, errorCode));
    }
}
