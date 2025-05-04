package kr.co.wanted.backend31.common;

import java.util.Map;

public class InternalErrorException extends BaseException {
    
    public InternalErrorException() {
        super(ErrorCode.INTERNAL_ERROR);
    }

    public InternalErrorException(Map<String, Object> detail) {
        super(ErrorCode.INTERNAL_ERROR, detail);
    }
}
