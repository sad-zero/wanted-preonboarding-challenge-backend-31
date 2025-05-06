package kr.co.wanted.backend31.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.wanted.backend31.common.model.product.Product;
import kr.co.wanted.backend31.common.model.product.specification.ProductCreateSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductManagementService {
    @Transactional
    public Product create(ProductCreateSpecification spec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}
