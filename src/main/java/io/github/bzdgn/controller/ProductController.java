package io.github.bzdgn.controller;

import io.github.bzdgn.dto.FilterRequest;
import io.github.bzdgn.entity.Product;
import io.github.bzdgn.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/filter")
    public List<Product> filterProducts(@RequestBody List<FilterRequest> filters) {
        return productService.filterProducts(filters);
    }
}
