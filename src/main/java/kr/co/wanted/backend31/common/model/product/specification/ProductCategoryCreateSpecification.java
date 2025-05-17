package kr.co.wanted.backend31.common.model.product.specification;

import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.function.Predicate;
import kr.co.wanted.backend31.common.error.InvariantContractException;
import kr.co.wanted.backend31.common.error.PreConditionContractException;
import kr.co.wanted.backend31.common.model.product.ProductCategory;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductCategoryCreateSpecification
 */
@Slf4j
@Builder
public record ProductCategoryCreateSpecification(boolean isPrimary,
                                                 @NotNull Long categoryId) implements
    Predicate<ProductCategory> {

  public ProductCategoryCreateSpecification {
    if (categoryId == null) {
      throw new InvariantContractException(this.getClass().getName(), Map.of("categoryId", "null"));
    }
  }

  @Override
  public boolean test(@NotNull ProductCategory t) {
    if (t == null) {
      throw new PreConditionContractException(Map.of("t", "null"));
    }

    final var result = isPrimary == t.isPrimary() && categoryId.equals(t.getCategory().getId());
    if (!result) {
      log.warn("validation fails");
    }
    return result;
  }
}
