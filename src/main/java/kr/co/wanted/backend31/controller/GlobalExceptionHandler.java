package kr.co.wanted.backend31.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.wanted.backend31.common.ConflictException;
import kr.co.wanted.backend31.common.ForbiddenException;
import kr.co.wanted.backend31.common.InternalErrorException;
import kr.co.wanted.backend31.common.InvalidInputException;
import kr.co.wanted.backend31.common.ResourceNotFoundException;
import kr.co.wanted.backend31.common.UnauthorizedException;
import kr.co.wanted.backend31.controller.vo.DetailError;
import kr.co.wanted.backend31.controller.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<?> handleUnexpectedException(RuntimeException e) {
        log.error("Unexpected exception occurs", e);
        final var resp = ErrorResponse.of(DetailError.of(new InternalErrorException(e)));
        return ResponseEntity.status(500)
                .body(resp);
    }

    @ExceptionHandler({ InternalErrorException.class })
    public ResponseEntity<?> handleServerException(InternalErrorException e) {
        log.warn("Internal server exception occurs", e);
        final var resp = ErrorResponse.of(DetailError.of(e));
        return ResponseEntity.status(Integer.valueOf(e.getErrorCode().getStatus()))
                .body(resp);
    }

    @ExceptionHandler({ UnauthorizedException.class })
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e) {
        log.warn("Unauthorized access occurs: {}", e.toString());
        final var resp = ErrorResponse.of(DetailError.of(e));
        return ResponseEntity.status(Integer.valueOf(e.getErrorCode().getStatus()))
                .body(resp);
    }

    @ExceptionHandler({ InvalidInputException.class })
    public ResponseEntity<?> handleInvalidInputException(InvalidInputException e) {
        log.info("Invalid input occurs: {}", e.toString());
        final var resp = ErrorResponse.of(DetailError.of(e));
        return ResponseEntity.status(Integer.valueOf(e.getErrorCode().getStatus()))
                .body(resp);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.info("Resource not found eccurs: {}", e.toString());
        final var resp = ErrorResponse.of(DetailError.of(e));
        return ResponseEntity.status(Integer.valueOf(e.getErrorCode().getStatus()))
                .body(resp);
    }

    @ExceptionHandler({ ForbiddenException.class })
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e) {
        log.warn("Forbidden access occurs: {}", e.toString());
        final var resp = ErrorResponse.of(DetailError.of(e));
        return ResponseEntity.status(Integer.valueOf(e.getErrorCode().getStatus()))
                .body(resp);
    }

    @ExceptionHandler({ ConflictException.class })
    public ResponseEntity<?> handleConflictException(ConflictException e) {
        log.info("Conflict occurs: {}", e.toString());
        final var resp = ErrorResponse.of(DetailError.of(e));
        return ResponseEntity.status(Integer.valueOf(e.getErrorCode().getStatus()))
                .body(resp);
    }

}
