package ecom_product.product.Service;

import ecom_product.product.DTO.ProductRequest;
import ecom_product.product.DTO.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse createProduct(ProductRequest productRequest);

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    List<ProductResponse> searchProduct(String keyword);
}
