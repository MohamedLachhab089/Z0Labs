package org.med.product.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.med.product.dto.ProductPurchaseRequest;
import org.med.product.dto.ProductPurchaseResponse;
import org.med.product.dto.ProductRequest;
import org.med.product.dto.ProductResponse;
import org.med.product.exception.ProductPurchaseException;
import org.med.product.mapper.ProductMapper;
import org.med.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toProductResponse)
        .toList();
  }

  public ProductResponse getProductById(Integer productId) {
    return productRepository
        .findById(productId)
        .map(productMapper::toProductResponse)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
  }

  public Integer createProduct(ProductRequest productRequest) {
    var product = productMapper.toProduct(productRequest);
    return productRepository.save(product).getId();
  }

  // Purchase of multiple products and updates their stock in the database
  @Transactional(rollbackFor = ProductPurchaseException.class)
  public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
    var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
    var storedProducts = productRepository.findAllByIdInOrderById(productIds);
    if (productIds.size() != storedProducts.size()) {
      throw new ProductPurchaseException("One or more products does not exist");
    }
    var sortedRequest =
        request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
    var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
    for (int i = 0; i < storedProducts.size(); i++) {
      var product = storedProducts.get(i);
      var productRequest = sortedRequest.get(i);
      if (product.getAvailableQuantity() < productRequest.quantity()) {
        throw new ProductPurchaseException(
            "Insufficient stock quantity for product with ID:: " + productRequest.productId());
      }
      var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
      product.setAvailableQuantity(newAvailableQuantity);
      productRepository.save(product);
      purchasedProducts.add(
          productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
    }
    return purchasedProducts;
  }
}
