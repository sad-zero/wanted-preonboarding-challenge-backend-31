package kr.co.wanted.backend31.common.entity.product.specification;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Predicate;

import kr.co.wanted.backend31.common.entity.product.ProductOption;
import lombok.Builder;

/**
 * ProductOptionCreateSpecification
 */
@Builder
public record ProductOptionCreateSpecification(String name, BigDecimal additionalPrice, String sku, Integer stock,
        Integer displayOrder) implements Predicate<ProductOption> {

    public ProductOptionCreateSpecification {
        Objects.requireNonNull(name);
        Objects.requireNonNull(additionalPrice);
        Objects.requireNonNull(sku);
        Objects.requireNonNull(stock);
        Objects.requireNonNull(displayOrder);
    }

    @Override
    public boolean test(ProductOption t) {
        return name.equals(t.getName())
                && additionalPrice.equals(t.getAdditionalPrice())
                && sku.equals(t.getSku())
                && stock.equals(t.getStock())
                && displayOrder.equals(t.getDisplayOrder());
    }

}
