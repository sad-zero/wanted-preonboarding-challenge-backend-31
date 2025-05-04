package kr.co.wanted.backend31.common;

import java.util.Map;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(Map<String, Object> detail) {
        super(ErrorCode.RESOURCE_NOT_FOUND, detail);
    }
}
