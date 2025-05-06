package kr.co.wanted.backend31.common.model.product.specification;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Predicate;

import kr.co.wanted.backend31.common.model.product.ProductPrice;
import lombok.Builder;

/**
 * ProductPriceCreateSpecification
 */
@Builder
public record ProductPriceCreateSpecification(BigDecimal basePrice, BigDecimal salePrice, BigDecimal costPrice,
        String currency, BigDecimal taxRate) implements Predicate<ProductPrice> {

    public ProductPriceCreateSpecification {
        Objects.requireNonNull(basePrice);
        Objects.requireNonNull(salePrice);
        Objects.requireNonNull(costPrice);
        Objects.requireNonNull(currency);
        Objects.requireNonNull(taxRate);
    }

    @Override
    public boolean test(ProductPrice t) {
        return basePrice.equals(t.getBasePrice())
                && salePrice.equals(t.getSalePrice())
                && costPrice.equals(t.getCostPrice())
                && currency.equals(t.getCurrency())
                && taxRate.equals(t.getTaxRate());
    }
}
