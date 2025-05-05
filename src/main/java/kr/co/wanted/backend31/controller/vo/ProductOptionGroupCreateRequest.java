package kr.co.wanted.backend31.controller.vo;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductOptionGroupCreateRequest(@NotBlank String name, @Positive int displayOrder,
        @Valid @NotNull List<@NotNull ProductOptionCreateRequest> options) {

}
