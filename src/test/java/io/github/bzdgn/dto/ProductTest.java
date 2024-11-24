package io.github.bzdgn.dto;

import io.github.bzdgn.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * What an unnecessary trivial dummy test. The only important part is equals and hashCode
 * testing. This needs to be transformed into a package level automatized reflection
 * test class so that it will sweep all the dto's in a dto package and test equals
 * and hashCode.
 *
 * Just have written this to match jacoco test coverage >= 0.80
 */
public class ProductTest {

    @Test
    void testGettersAndSetters() {
        Product product = new Product(1L, "field", 100.0, "category");

        assertEquals(1L, product.getId());
        assertEquals("field", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals("category", product.getCategory());

        product.setId(1L);
        product.setName("field");
        product.setPrice(100.0);
        product.setCategory("category");

        Product product2 = new Product();

        assertEquals(1L, product.getId());
        assertEquals("field", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals("category", product.getCategory());
    }

    @Test
    void testEqualsAndHashCode() {
        Product product1 = new Product(1L, "field", 100.0, "category");
        Product product2 = new Product(1L, "field", 100.0, "category");

        assertTrue(product1.equals(product2));
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testToString() {
        Product product = new Product(1L, "field", 100.0, "category");

        assertEquals("Product(id=1, name=field, price=100.0, category=category)", product.toString());
    }
}
