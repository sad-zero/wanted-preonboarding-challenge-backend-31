package kr.co.wanted.backend31.common.model.product.specification;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import kr.co.wanted.backend31.common.model.product.ProductDetail;
import lombok.Builder;

/**
 * ProductDetailCreateSpecification
 */
@Builder
public record ProductDetailCreateSpecification(BigDecimal weight, Map<String, Object> dimensions, String materials,
        String countryOfOrigin, String warrantyInfo, String careInstructions, Map<String, Object> additionalInfo)
        implements Predicate<ProductDetail> {

    public ProductDetailCreateSpecification {
        Objects.requireNonNull(weight);
        Objects.requireNonNull(dimensions);
        Objects.requireNonNull(materials);
        Objects.requireNonNull(countryOfOrigin);
        Objects.requireNonNull(warrantyInfo);
        Objects.requireNonNull(careInstructions);
        Objects.requireNonNull(additionalInfo);
    }

    @Override
    public boolean test(ProductDetail t) {
        return weight.equals(t.getWeight())
                && dimensions.equals(t.getDimensions())
                && materials.equals(t.getMaterials())
                && countryOfOrigin.equals(t.getCountryOfOrigin())
                && warrantyInfo.equals(t.getWarrantyInfo())
                && careInstructions.equals(t.getCareInstructions())
                && additionalInfo.equals(t.getAdditionalInfo());
    }
}
