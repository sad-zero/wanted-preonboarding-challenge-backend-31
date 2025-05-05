package kr.co.wanted.backend31.controller.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductOptionCreateRequest(@NotBlank String name, int additionalPrice, @NotBlank String sku,
        @PositiveOrZero int stock, @Positive int displayOrder) {

}
