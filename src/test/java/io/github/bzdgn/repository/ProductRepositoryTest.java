package io.github.bzdgn.repository;

import io.github.bzdgn.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindAll() {
        List<Product> products = productRepository.findAll();

        assertEquals(6, products.size());
    }

    @Test
    void testFindByID() {
        Optional<Product> product = productRepository.findById(1L);

        product.ifPresent((p) -> {
            assertNotNull(product);
            assertEquals(1L, p.getId());
            assertEquals("Laptop", p.getName());
            assertEquals(1500.0, p.getPrice());
            assertEquals("electronics", p.getCategory());
        });
    }

    @Test
    void testIDGeneration() {
        Product productCreated = new Product();
        productCreated.setName("Audio Editor");
        productCreated.setPrice(88.9);
        productCreated.setCategory("Software");

        productRepository.saveAndFlush(productCreated);


        Optional<Product> product = productRepository.findOne((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), productCreated.getName()));

        product.ifPresent((p) -> {
            assertNotNull(product);
            assertEquals(productCreated.getId(), p.getId());
            assertEquals(productCreated.getName(), p.getName());
            assertEquals(productCreated.getPrice(), p.getPrice());
            assertEquals(productCreated.getCategory(), p.getCategory());
        });
    }

}
