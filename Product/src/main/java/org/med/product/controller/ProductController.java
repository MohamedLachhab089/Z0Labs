package org.med.product.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.med.product.dto.ProductPurchaseRequest;
import org.med.product.dto.ProductPurchaseResponse;
import org.med.product.dto.ProductRequest;
import org.med.product.dto.ProductResponse;
import org.med.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{product-id}")
  public ResponseEntity<ProductResponse> getProductById(
      @PathVariable("product-id") Integer productId) {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @PostMapping
  public ResponseEntity<Integer> addProduct(@RequestBody @Valid ProductRequest productRequest) {
    return ResponseEntity.ok(productService.createProduct(productRequest));
  }

  @PostMapping("/purchase")
  public ResponseEntity<List<ProductPurchaseResponse>> purchase(
      @RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequest) {
    return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequest));
  }
}
