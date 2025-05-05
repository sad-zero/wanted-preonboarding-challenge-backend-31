package kr.co.wanted.backend31.controller.vo;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(@NotBlank String name, @NotBlank String slug, @NotBlank String shortDescription,
                @NotBlank String fullDescription,
                int sellerId, int brandId, @NotBlank String status, @NotNull ProductDetailCreateRequest detail,
                @NotNull ProductPriceCreateRequest price,
                @NotNull @Valid List<@NotNull ProductCategoryCreateRequest> categories,
                @NotNull @Valid List<@NotNull ProductOptionGroupCreateRequest> optionGroups,
                @NotNull @Valid List<@NotNull ProductImageCreateRequest> images, @NotNull List<@NotNull Integer> tags) {
}
