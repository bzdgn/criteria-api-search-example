package io.github.bzdgn.specification;

import io.github.bzdgn.entity.Product;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> contains(String field, String value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(field), "%" + value + "%");
    }

    public static Specification<Product> notContains(String field, String value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notLike(root.get(field), "%" + value + "%");
    }

    public static Specification<Product> greaterThan(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.get(field), value);
    }

    public static Specification<Product> greaterThanOrEquals(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.ge(root.get(field), value);
    }

    public static Specification<Product> lessThan(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lt(root.get(field), value);
    }

    public static Specification<Product> lessThanOrEquals(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.le(root.get(field), value);
    }

    public static Specification<Product> equals(String field, Object value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(field), value);
    }

}

