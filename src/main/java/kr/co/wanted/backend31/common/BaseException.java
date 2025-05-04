package kr.co.wanted.backend31.common;

import java.util.Map;
import java.util.Objects;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Map<String, Object> detail;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        Objects.requireNonNull(errorCode);
        this.errorCode = errorCode;
        this.detail = null;
    }

    public BaseException(ErrorCode errorCode, Map<String, Object> detail) {
        super(errorCode.getMessage());
        Objects.requireNonNull(errorCode);
        Objects.requireNonNull(detail);
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
