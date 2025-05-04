package kr.co.wanted.backend31.common;

import java.util.Map;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(Map<String, Object> detail) {
        super(ErrorCode.UNAUTHORIZED, detail);
    }

}
