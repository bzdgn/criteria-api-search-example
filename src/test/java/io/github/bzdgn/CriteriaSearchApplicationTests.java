package io.github.bzdgn;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class CriteriaSearchApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Assert that the application context is loaded
        assertNotNull(applicationContext, "ApplicationContext should have been loaded.");
    }

    @Test
    void specificBeanIsLoaded() {
        // Check if a specific Bean exists in the context
        assertNotNull(applicationContext.getBean("productRepository"), "ProductRepository bean should exist");
        assertNotNull(applicationContext.getBean("productService"), "ProductService bean should exist");
        assertNotNull(applicationContext.getBean("productController"), "productController bean should exist");
    }

    @Test
    void testMainMethod() {
        try (MockedStatic<SpringApplication> mockedStatic = mockStatic(SpringApplication.class)) {
            CriteriaSearchApplication.main(new String[]{});
            mockedStatic.verify(() -> SpringApplication.run(CriteriaSearchApplication.class, new String[]{}), Mockito.times(1));
        }
    }

}
