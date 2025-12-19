package ecom_product.product.Controller;


import ecom_product.product.DTO.ProductRequest;
import ecom_product.product.DTO.ProductResponse;
import ecom_product.product.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private  final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>>getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());

    }
    @PostMapping()
    public ResponseEntity<ProductResponse>createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse>updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword)
    {
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }




}
