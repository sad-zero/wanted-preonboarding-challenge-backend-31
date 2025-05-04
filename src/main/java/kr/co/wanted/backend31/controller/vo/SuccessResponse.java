package kr.co.wanted.backend31.controller.vo;

import java.util.Objects;

public record SuccessResponse<T>(T data, String message, boolean success) {
    public SuccessResponse {
        if (!success) {
            throw new IllegalArgumentException("success should be true");
        }
        Objects.requireNonNull(data);
        Objects.requireNonNull(message);
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<T>(data, "요청이 성공적으로 처리되었습니다.", true);
    }
}
