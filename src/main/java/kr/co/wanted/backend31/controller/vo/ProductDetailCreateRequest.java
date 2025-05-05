package kr.co.wanted.backend31.controller.vo;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDetailCreateRequest(float weight, @NotNull ProductDetailDimensionCreateRequest dimensions,
        @NotBlank String materials, @NotBlank String countryOfOrigin, @NotBlank String warrantyInfo,
        @NotBlank String careInstructions, @NotNull Map<String, Object> additionalInfo) {

}
