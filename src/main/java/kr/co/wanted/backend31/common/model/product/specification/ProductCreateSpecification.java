package kr.co.wanted.backend31.common.model.product.specification;

import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import kr.co.wanted.backend31.common.model.product.Product;
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

  public ProductCreateSpecification {
    final var invariantFails = new HashMap<String, String>();
    if (name == null) {
      invariantFails.put("name", "null");
    }
    if (slug == null) {
      invariantFails.put("slug", "null");
    }
    if (shortDescription == null) {
      invariantFails.put("shortDescription", "null");
    }
    if (fullDescription == null) {
      invariantFails.put("fullDescription", "null");
    }
    if (status == null) {
      invariantFails.put("status", "null");
    }
    Objects.requireNonNull(detailSpec);
    Objects.requireNonNull(priceSpec);
    Objects.requireNonNull(sellerId);
    Objects.requireNonNull(brandId);
    categorySpecs = Collections.unmodifiableList(categorySpecs);
    categorySpecs.forEach(Objects::requireNonNull);
    optionGroupSpecs = Collections.unmodifiableList(optionGroupSpecs);
    optionGroupSpecs.forEach(Objects::requireNonNull);
    imageSpecs = Collections.unmodifiableList(imageSpecs);
    imageSpecs.forEach(Objects::requireNonNull);
    tagSpecs = Collections.unmodifiableList(tagSpecs);
    tagSpecs.forEach(Objects::requireNonNull);
  }

  @Override
  public boolean test(Product t) {
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
