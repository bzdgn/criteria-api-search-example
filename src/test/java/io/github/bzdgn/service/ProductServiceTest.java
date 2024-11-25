package io.github.bzdgn.service;

import io.github.bzdgn.dto.FilterRequest;
import io.github.bzdgn.entity.Product;
import io.github.bzdgn.exception.InvalidFilterException;
import io.github.bzdgn.exception.InvalidSearchParameterFieldException;
import io.github.bzdgn.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    // Valid filters
    @Test
    void testNoFilters() {
        // Arrange
        List<FilterRequest> filters = new ArrayList<>();

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(6, result.size());
    }

    @Test
    void testFiltersWithSingleContains() {
        // Arrange
        List<FilterRequest> filters = List.of(new FilterRequest("category", "contains", "electronics"));

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testFiltersWithMultipleContains() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("name", "contains", "ACME"),
                new FilterRequest("name", "contains", "Draw")
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ACME Draw", result.get(0).getName());
        assertEquals(278, result.get(0).getPrice());
        assertEquals("software", result.get(0).getCategory());
    }

    @Test
    void testFiltersWithNotContains() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("name", "contains", "ACME"),
                new FilterRequest("name", "not contains", "Draw")
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testFiltersWithGreater() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("price", "greater than", 120)
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void testFiltersWithGreaterThan() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("price", "greater than or equals", 120)
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    @Test
    void testFiltersWithLessThan() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("price", "less than", 99)
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFiltersWithLessThanOrEquals() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("price", "less than or equals", 99)
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testFiltersWithEquals() {
        // Arrange
        List<FilterRequest> filters = List.of(
                new FilterRequest("category", "equals", "software")
        );

        // Act
        List<Product> result = productService.filterProducts(filters);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    // Invalid Filters
    @Test
    void testInvalidOperator() {
        // Arrange
        List<FilterRequest> filters = new ArrayList<>();
        filters.add(new FilterRequest("price", "invalid_operator", 100));

        // Act & Assert
        InvalidFilterException exception = assertThrows(
                InvalidFilterException.class,
                () -> productService.filterProducts(filters)
        );

        assertEquals("Invalid operator: invalid_operator", exception.getMessage());
    }

    @Test
    void testInvalidField() {
        // Arrange
        List<FilterRequest> filters = new ArrayList<>();
        filters.add(new FilterRequest("invalid_field", "=", 100));

        // Act & Assert
        InvalidSearchParameterFieldException exception = assertThrows(
                InvalidSearchParameterFieldException.class,
                () -> productService.filterProducts(filters)
        );

        assertEquals("Could not resolve attribute 'invalid_field' of 'io.github.bzdgn.entity.Product'", exception.getMessage());
    }

}
