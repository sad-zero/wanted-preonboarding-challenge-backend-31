package kr.co.wanted.backend31.common.entity.product.specification;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import kr.co.wanted.backend31.common.entity.product.ProductImage;
import lombok.Builder;

/**
 * ProductImageCreateSpecification
 */
@Builder
public record ProductImageCreateSpecification(String url, String altText, boolean isPrimary, Integer displayOrder,
        Long optionId) implements Predicate<ProductImage> {

    public ProductImageCreateSpecification {
        Objects.requireNonNull(url);
        Objects.requireNonNull(altText);
        Objects.requireNonNull(displayOrder);
    }

    @Override
    public boolean test(ProductImage t) {
        return url.equals(t.getUrl())
                && altText.equals(t.getAltText())
                && isPrimary == t.isPrimary()
                && displayOrder.equals(t.getDisplayOrder())
                && Optional.ofNullable(optionId).stream().allMatch(id -> id.equals(t.getOption().getId()));
    }
}
