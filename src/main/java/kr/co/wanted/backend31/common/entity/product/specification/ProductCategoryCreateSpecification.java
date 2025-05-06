package kr.co.wanted.backend31.common.entity.product.specification;

import java.util.Objects;
import java.util.function.Predicate;

import kr.co.wanted.backend31.common.entity.category.ProductCategory;
import lombok.Builder;

/**
 * ProductCategoryCreateSpecification
 */
@Builder
public record ProductCategoryCreateSpecification(boolean isPrimary, Long categoryId)
        implements Predicate<ProductCategory> {

    public ProductCategoryCreateSpecification {
        Objects.requireNonNull(categoryId);
    }

    @Override
    public boolean test(ProductCategory t) {
        return isPrimary == t.isPrimary()
                && categoryId.equals(t.getCategory().getId());
    }
}
