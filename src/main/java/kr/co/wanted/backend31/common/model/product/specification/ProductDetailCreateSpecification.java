package kr.co.wanted.backend31.common.model.product.specification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Predicate;
import kr.co.wanted.backend31.common.model.product.ProductDetail;
import kr.co.wanted.backend31.common.util.ContractValidator;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductDetailCreateSpecification
 */
@Slf4j
@Builder
public record ProductDetailCreateSpecification(@NotNull BigDecimal weight,
                                               @NotNull Map<String, Object> dimensions,
                                               @NotBlank String materials,
                                               @NotBlank String countryOfOrigin,
                                               @NotBlank String warrantyInfo,
                                               @NotBlank String careInstructions,
                                               @NotNull Map<String, Object> additionalInfo) implements
    Predicate<ProductDetail> {

  public static ProductDetailCreateSpecification create(@NotNull BigDecimal weight,
      @NotNull Map<String, Object> dimensions, @NotBlank String materials,
      @NotBlank String countryOfOrigin, @NotBlank String warrantyInfo,
      @NotBlank String careInstructions, @NotNull Map<String, Object> additionalInfo) {
    final var spec = ProductDetailCreateSpecification.builder().weight(weight)
        .dimensions(dimensions).materials(materials).countryOfOrigin(countryOfOrigin)
        .warrantyInfo(warrantyInfo).careInstructions(careInstructions)
        .additionalInfo(additionalInfo).build();
    ContractValidator.<ProductDetailCreateSpecification>invariantCondition(
        ProductDetailCreateSpecification.class.getName()).check(spec);
    return spec;
  }

  @Override
  public boolean test(@NotNull ProductDetail t) {
    final var operationPath = ProductDetailCreateSpecification.class.getName() + ".test";
    ContractValidator.<ProductDetail>preCondition(operationPath).check(t);

    final var result =
        weight.equals(t.getWeight()) && dimensions.equals(t.getDimensions()) && materials.equals(
            t.getMaterials()) && countryOfOrigin.equals(t.getCountryOfOrigin())
            && warrantyInfo.equals(t.getWarrantyInfo()) && careInstructions.equals(
            t.getCareInstructions()) && additionalInfo.equals(t.getAdditionalInfo());
    if (!result) {
      log.warn("validation fails");
    }
    return result;
  }
}
