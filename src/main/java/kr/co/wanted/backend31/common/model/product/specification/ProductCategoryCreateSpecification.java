package kr.co.wanted.backend31.common.model.product.specification;

import jakarta.validation.constraints.NotNull;
import java.util.function.Predicate;
import kr.co.wanted.backend31.common.model.product.ProductCategory;
import kr.co.wanted.backend31.common.util.ContractValidator;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

/**
 * ProductCategoryCreateSpecification
 */
@Slf4j
@Builder
public record ProductCategoryCreateSpecification(boolean isPrimary,
                                                 @NotNull Long categoryId) implements
    Predicate<ProductCategory> {

  /**
   * Specification Factory
   *
   * @param isPrimary  isPrimary
   * @param categoryId category's id
   * @return validated specification
   */
  @Validated
  public static ProductCategoryCreateSpecification create(boolean isPrimary,
      @NotNull Long categoryId) {
    final var spec = new ProductCategoryCreateSpecification(isPrimary, categoryId);
    ContractValidator.<ProductCategoryCreateSpecification>invariantCondition(
        ProductCategoryCreateSpecification.class.getName()).check(spec);
    return spec;
  }

  @Override
  public boolean test(ProductCategory t) {
    final var operationPath = ProductCategory.class.getName() + ".test";
    ContractValidator.<ProductCategory>preCondition(operationPath).check(t);

    final var result = isPrimary == t.isPrimary() && categoryId.equals(t.getCategory().getId());
    if (!result) {
      log.warn("validation fails");
    }

    return result;
  }
}
