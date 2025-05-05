package kr.co.wanted.backend31.controller.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductPriceCreateRequest(@PositiveOrZero int basePrice, @PositiveOrZero int salePrice,
        @PositiveOrZero int costPrice, @NotBlank String currency, @PositiveOrZero int taxRate) {

}
