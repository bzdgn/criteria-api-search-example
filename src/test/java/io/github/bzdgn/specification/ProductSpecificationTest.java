//package io.github.bzdgn.specification;
//
//import io.github.bzdgn.entity.Product;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ProductSpecificationTest {
//
//    @Test
//    void testContains() {
//        // Arrange
//        Root<Product> root = mock(Root.class);
//        CriteriaQuery<?> query = mock(CriteriaQuery.class);
//        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
//        Predicate predicate = mock(Predicate.class);
//
//        when(root.get("name")).thenReturn(null); // Mock field access
//        when(criteriaBuilder.like(any(), eq("%value%"))).thenReturn(predicate);
//
//        // Act
//        Specification<Product> spec = ProductSpecification.contains("name", "value");
//        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(predicate, result);
//        verify(criteriaBuilder).like(any(), eq("%value%"));
//    }
//
//    @Test
//    void testNotContains() {
//        // Arrange
//        Root<Product> root = mock(Root.class);
//        CriteriaQuery<?> query = mock(CriteriaQuery.class);
//        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
//        Predicate predicate = mock(Predicate.class);
//
//        when(root.get("category")).thenReturn(null); // Mock field access
//        when(criteriaBuilder.notLike(any(), eq("%excluded%"))).thenReturn(predicate);
//
//        // Act
//        Specification<Product> spec = ProductSpecification.notContains("category", "excluded");
//        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(predicate, result);
//        verify(criteriaBuilder).notLike(any(), eq("%excluded%"));
//    }
//
//    @Test
//    void testGreaterThan() {
//        // Arrange
//        Root<Product> root = mock(Root.class);
//        CriteriaQuery<?> query = mock(CriteriaQuery.class);
//        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
//        Predicate predicate = mock(Predicate.class);
//
//        when(root.get("price")).thenReturn(null); // Mock field access
//        when(criteriaBuilder.gt(any(), eq(100.0))).thenReturn(predicate);
//
//        // Act
//        Specification<Product> spec = ProductSpecification.greaterThan("price", 100.0);
//        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(predicate, result);
//        verify(criteriaBuilder).gt(any(), eq(100.0));
//    }
//
//    @Test
//    void testEquals() {
//        // Arrange
//        Root<Product> root = mock(Root.class);
//        CriteriaQuery<?> query = mock(CriteriaQuery.class);
//        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
//        Predicate predicate = mock(Predicate.class);
//
//        when(root.get("id")).thenReturn(null); // Mock field access
//        when(criteriaBuilder.equal(any(), eq(1L))).thenReturn(predicate);
//
//        // Act
//        Specification<Product> spec = ProductSpecification.equals("id", 1L);
//        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(predicate, result);
//        verify(criteriaBuilder).equal(any(), eq(1L));
//    }
//}
