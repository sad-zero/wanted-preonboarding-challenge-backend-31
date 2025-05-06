package kr.co.wanted.backend31.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.wanted.backend31.common.model.product.ProductTag;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

}
