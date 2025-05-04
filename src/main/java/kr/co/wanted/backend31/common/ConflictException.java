package kr.co.wanted.backend31.common;

import java.util.Map;

public class ConflictException extends BaseException {

    public ConflictException() {
        super(ErrorCode.CONFLICT);
    }

    public ConflictException(Map<String, Object> detail) {
        super(ErrorCode.CONFLICT, detail);
    }
}
