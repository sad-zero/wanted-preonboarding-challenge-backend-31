package kr.co.wanted.backend31.controller.vo;

import org.hibernate.validator.constraints.URL;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductImageCreateRequest(@URL String url, @NotBlank String altText, boolean isPrimary,
        @Positive int displayOrder, @Nullable Integer optionId) {

}
