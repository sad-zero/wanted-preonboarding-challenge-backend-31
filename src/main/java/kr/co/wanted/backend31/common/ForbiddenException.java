package kr.co.wanted.backend31.common;

import java.util.Map;

public class ForbiddenException extends BaseException {

    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
    
    public ForbiddenException(Map<String, Object> detail) {
        super(ErrorCode.FORBIDDEN, detail);
    }
}
