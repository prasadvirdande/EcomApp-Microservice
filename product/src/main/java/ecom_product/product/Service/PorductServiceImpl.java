package ecom_product.product.Service;

import ecom_product.product.DTO.ProductRequest;
import ecom_product.product.DTO.ProductResponse;
import ecom_product.product.model.Product;
import ecom_product.product.Repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PorductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product > products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();

    }


    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found with id " + id);
        }
        productRepository.deleteById(id);

    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
      Optional<Product> product=productRepository.findById(id);
      if(product.isEmpty()){
        throw new RuntimeException("Product not found with id " + id);
      }
        Product product1=product.get();
        product1.setName(productRequest.getName());
        product1.setDescription(productRequest.getDescription());
        product1.setPrice(productRequest.getPrice());
        product1.setStockQuantity(productRequest.getStockQuantity());
        product1.setCategory(productRequest.getCategory());
        product1.setImageUrl(productRequest.getImageUrl());
        return mapToProductResponse(productRepository.save(product1));
    }

    @Override
    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.findyByKeyWord(keyword)
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }


    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Optional<Product> product = productRepository.findByName(productRequest.getName());
        if (product.isPresent()) {
            throw new RuntimeException("Product already exists");
        }
        Product product1 = new Product();
        product1.setName(productRequest.getName());
        product1.setDescription(productRequest.getDescription());
        product1.setPrice(productRequest.getPrice());
        product1.setStockQuantity(productRequest.getStockQuantity());
        product1.setCategory(productRequest.getCategory());
        product1.setImageUrl(productRequest.getImageUrl());
        return mapToProductResponse(productRepository.save(product1));
    }


    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
       productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setCategory(product.getCategory());
        productResponse.setImageUrl(product.getImageUrl());
       productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
        return productResponse;

    }
}
