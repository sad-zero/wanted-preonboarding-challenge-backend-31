package kr.co.wanted.backend31.common.model.product.specification;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import kr.co.wanted.backend31.common.model.product.Product;
import kr.co.wanted.backend31.common.util.ContractValidator;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public record ProductCreateSpecification(@NotNull String name, @NotNull String slug,
                                         @NotNull String shortDescription,
                                         @NotNull String fullDescription, @NotNull Long sellerId,
                                         @NotNull Long brandId, @NotNull String status,
                                         @NotNull ProductDetailCreateSpecification detailSpec,
                                         @NotNull ProductPriceCreateSpecification priceSpec,
                                         @NotNull List<ProductCategoryCreateSpecification> categorySpecs,
                                         @NotNull List<ProductOptionGroupCreateSpecification> optionGroupSpecs,
                                         @NotNull List<ProductImageCreateSpecification> imageSpecs,
                                         @NotNull List<ProductTagCreateSpecification> tagSpecs) implements
    Predicate<Product> {

  public static ProductCreateSpecification create(@NotNull String name, @NotNull String slug,
      @NotNull String shortDescription, @NotNull String fullDescription, @NotNull Long sellerId,
      @NotNull Long brandId, @NotNull String status,
      @NotNull ProductDetailCreateSpecification detailSpec,
      @NotNull ProductPriceCreateSpecification priceSpec,
      @NotNull List<ProductCategoryCreateSpecification> categorySpecs,
      @NotNull List<ProductOptionGroupCreateSpecification> optionGroupSpecs,
      @NotNull List<ProductImageCreateSpecification> imageSpecs,
      @NotNull List<ProductTagCreateSpecification> tagSpecs) {
    final var spec = new ProductCreateSpecification(name, slug, shortDescription, fullDescription,
        sellerId, brandId, status, detailSpec, priceSpec, categorySpecs, optionGroupSpecs,
        imageSpecs, tagSpecs);
    ContractValidator.<ProductCreateSpecification>invariantCondition(ProductCreateSpecification.class.getName()).check(spec);
    return spec;
  }

  @Override
  public boolean test(@NotNull Product t) {
    final var operationPath = Product.class.getName() + ".test";
    ContractValidator.<Product>preCondition(operationPath).check(t);

    record Tuple<T, P extends Predicate<T>>(T target, P spec) {

      public Tuple {
        Objects.requireNonNull(target);
        Objects.requireNonNull(spec);
      }

      public Tuple(Map.Entry<T, P> entry) {
        this(entry.getKey(), entry.getValue());
      }

      public static <T, P extends Predicate<T>> List<Tuple<T, P>> zip(List<P> specs,
          List<T> targets) {
        return IntStream.range(0, specs.size()).boxed()
            .collect(Collectors.toMap(targets::get, specs::get)).entrySet().stream().map(Tuple::new)
            .toList();
      }

      public boolean test() {
        return spec.test(target);
      }
    }

    final var result =
        name.equals(t.getName()) && slug.equals(t.getSlug()) && shortDescription.equals(
            t.getShortDescription()) && fullDescription.equals(t.getFullDescription())
            && sellerId.equals(t.getSeller().getId()) && brandId.equals(t.getBrand().getId())
            && status.equals(t.getStatus()) && detailSpec.test(t.getDetail()) && priceSpec.test(
            t.getPrice()) && Tuple.zip(categorySpecs, t.getCategories()).stream()
            .allMatch(Tuple::test) && Tuple.zip(optionGroupSpecs, t.getOptionGroups()).stream()
            .allMatch(Tuple::test) && Tuple.zip(imageSpecs, t.getImages()).stream()
            .allMatch(Tuple::test) && Tuple.zip(tagSpecs, t.getTags()).stream()
            .allMatch(Tuple::test);
    if (!result) {
      log.warn("validation fails");
    }
    return result;
  }

}
