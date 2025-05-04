package kr.co.wanted.backend31.common;

import java.util.Map;

public class InvalidInputException extends BaseException {

    public InvalidInputException() {
        super(ErrorCode.INVALID_INPUT);
    }

    public InvalidInputException(Map<String, Object> detail) {
        super(ErrorCode.INVALID_INPUT, detail);
    }

}
