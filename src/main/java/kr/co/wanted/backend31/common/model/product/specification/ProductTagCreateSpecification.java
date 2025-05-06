package kr.co.wanted.backend31.common.model.product.specification;

import java.util.Objects;
import java.util.function.Predicate;

import kr.co.wanted.backend31.common.model.tag.ProductTag;
import lombok.Builder;

/**
 * ProductTagCreateSpecification
 */
@Builder
public record ProductTagCreateSpecification(Long tagId) implements Predicate<ProductTag> {

    public ProductTagCreateSpecification {
        Objects.requireNonNull(tagId);
    }

    @Override
    public boolean test(ProductTag t) {
        return tagId.equals(t.getTag().getId());
    }

}
