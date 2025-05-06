package kr.co.wanted.backend31.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.wanted.backend31.controller.vo.ProductCreateRequest;
import kr.co.wanted.backend31.controller.vo.ProductCreateResponse;
import kr.co.wanted.backend31.controller.vo.SuccessResponse;
import kr.co.wanted.backend31.service.ProductManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductManagementController {
    private final ProductManagementService managementService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<ProductCreateResponse>> postMethodName(
            @Valid @RequestBody ProductCreateRequest request) {
        log.info("POST /products: {}", request);
        final var spec = request.to();
        final var product = managementService.create(spec);
        final var resp = ProductCreateResponse.of(product);
        log.info("new product created: {}", resp);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.of(resp));
    }
}
