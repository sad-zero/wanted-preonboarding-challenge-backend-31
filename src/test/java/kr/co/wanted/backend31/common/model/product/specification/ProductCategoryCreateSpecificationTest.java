package kr.co.wanted.backend31.common.model.product.specification;

import static org.junit.jupiter.api.Assertions.assertThrows;

import kr.co.wanted.backend31.common.error.InvariantContractException;
import kr.co.wanted.backend31.common.error.PreConditionContractException;
import kr.co.wanted.backend31.common.model.category.Category;
import kr.co.wanted.backend31.common.model.product.ProductCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductCategoryCreateSpecificationTest {

  @Test
  void createInvariantSpec() {
    // given
    final var isPrimary = true;
    final Long categoryId = null;
    // when
    assertThrows(InvariantContractException.class,
        () -> ProductCategoryCreateSpecification.create(isPrimary, categoryId));
  }

  @Test
  void createValidSpec() {
    // given
    final var isPrimary = true;
    final Long categoryId = 1L;
    //when
    final var spec = ProductCategoryCreateSpecification.create(isPrimary, categoryId);
    // then
    Assertions.assertThat(spec).isNotNull();
  }

  @Test
  void testInvalidPrecondition() {
    // given
    final var isPrimary = true;
    final Long categoryId = 1L;
    final var spec = ProductCategoryCreateSpecification.create(isPrimary, categoryId);

    // when
    assertThrows(PreConditionContractException.class, () -> spec.test(null));
  }

  @Test
  void testValidPrecondition() {
    // given
    final var isPrimary = true;
    final Long categoryId = 1L;
    final var spec = ProductCategoryCreateSpecification.create(isPrimary, categoryId);

    final var category = new Category(categoryId, null, null, null, null, null, null, null, null);
    final var target = ProductCategory.builder().isPrimary(!isPrimary).category(category).build();

    // when
    Assertions.assertThat(spec.test(target)).isFalse();
  }
}