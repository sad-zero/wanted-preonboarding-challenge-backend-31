package kr.co.wanted.backend31.controller.vo;

import java.time.LocalDateTime;

public record ProductCreateResponse(int id, String name, String slug, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
