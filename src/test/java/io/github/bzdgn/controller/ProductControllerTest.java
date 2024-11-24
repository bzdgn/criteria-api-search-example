package io.github.bzdgn.controller;

import io.github.bzdgn.dto.FilterRequest;
import io.github.bzdgn.entity.Product;
import io.github.bzdgn.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterProducts_WithValidFilters() {
        // Arrange
        List<FilterRequest> filters = new ArrayList<>();
        filters.add(new FilterRequest("name", "contains", "Test"));

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1L, "Test Product", 100.0, "electronics"));

        when(productService.filterProducts(filters)).thenReturn(mockProducts);

        // Act
        List<Product> response = productController.filterProducts(filters);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Test Product", response.get(0).getName());
        assertEquals(100.0, response.get(0).getPrice());
        assertEquals("electronics", response.get(0).getCategory());

        verify(productService, times(1)).filterProducts(filters);
    }

    @Test
    void testFilterProducts_WithEmptyFilters() {
        // Arrange
        List<FilterRequest> filters = new ArrayList<>();
        List<Product> mockProducts = new ArrayList<>();

        when(productService.filterProducts(filters)).thenReturn(mockProducts);

        // Act
        List<Product> response = productController.filterProducts(filters);

        // Assert
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(productService, times(1)).filterProducts(filters);
    }

    @Test
    void testFilterProducts_WithNullFilters() {
        // Arrange
        when(productService.filterProducts(null)).thenThrow(new IllegalArgumentException("Filters cannot be null"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productController.filterProducts(null)
        );

        assertEquals("Filters cannot be null", exception.getMessage());
        verify(productService, times(1)).filterProducts(null);
    }

}
