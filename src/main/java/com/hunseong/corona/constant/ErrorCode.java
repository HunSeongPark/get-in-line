package com.hunseong.corona.constant;

import com.hunseong.corona.exception.GeneralException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Hunseong on 2022/05/02
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, HttpStatus.OK, "OK"),

    BAD_REQUEST(10000, HttpStatus.BAD_REQUEST, "Bad Request"),
    SPRING_BAD_REQUEST(10001, HttpStatus.BAD_REQUEST, "Spring-detected Bad Request"),
    VALIDATION_ERROR(10002, HttpStatus.BAD_REQUEST, "Validation Error"),
    NOT_FOUND(10004, HttpStatus.NOT_FOUND, "Requested resource is not found"),

    INTERNAL_ERROR(20000, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    SPRING_INTERNAL_ERROR(20001, HttpStatus.INTERNAL_SERVER_ERROR, "Spring-detected Internal Error"),
    DATA_ACCESS_ERROR(20002, HttpStatus.INTERNAL_SERVER_ERROR, "Data Access Error")
    ;


    private final Integer code;
    private final HttpStatus status;
    private final String message;

    public static ErrorCode fromStatus(HttpStatus status) {

        if (status == null) {
            throw new GeneralException("HttpStatus is null.");
        }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getStatus() == status)
                .findFirst()
                .orElseGet(() -> {
                    if (status.is4xxClientError()) { return ErrorCode.BAD_REQUEST; }
                    else if( status.is5xxServerError()) { return ErrorCode.INTERNAL_ERROR; }
                    else return ErrorCode.OK;
                });
    }

    public String getMessage(Throwable e) {
        return this.getMessage() + " - " + e.getMessage();
    }

    // message가 blank일 경우 ErrorCode의 메시지가 return
    public String getMessage(String message) {
        if (hasText(message)) {
            return message;
        } else {
            return this.getMessage();
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }
}
