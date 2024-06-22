package com.discphy.standard.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@JsonInclude(NON_NULL)
public class ApiResponse<T> {

    private final int code;
    private final String message;
    private final T result;

    public static ApiResponse<?> fail(HttpStatus status, String message) {
        return ApiResponse.builder()
                .code(status.value())
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> success(T result) {
        return ApiResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(result)
                .build();
    }
}
