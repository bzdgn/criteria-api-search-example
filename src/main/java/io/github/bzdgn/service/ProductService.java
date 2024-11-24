package io.github.bzdgn.service;

import io.github.bzdgn.dto.FilterRequest;
import io.github.bzdgn.entity.Product;
import io.github.bzdgn.exception.InvalidFilterException;
import io.github.bzdgn.repository.ProductRepository;
import io.github.bzdgn.specification.Operator;
import io.github.bzdgn.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> filterProducts(List<FilterRequest> filters) {
        Specification<Product> spec = Specification.where(null);

        for (FilterRequest filter : filters) {
            spec = spec.and(chainSpecs(filter.getField(), filter.getOperator(), filter.getValue()));
        }

        return productRepository.findAll(spec);
    }

    static Specification<Product> chainSpecs(String field, String operator, Object value) {
        Operator op = Operator.fromString(operator);
        return switch (op) {
            case CONTAINS -> ProductSpecification.contains(field, (String) value);
            case NOT_CONTAINS -> ProductSpecification.notContains(field, (String) value);
            case GREATER_THAN -> ProductSpecification.greaterThan(field, (Number) value);
            case GREATER_THAN_OR_EQUALS -> ProductSpecification.greaterThanOrEquals(field, (Number) value);
            case LESS_THAN -> ProductSpecification.lessThan(field, (Number) value);
            case LESS_THAN_OR_EQUALS -> ProductSpecification.lessThanOrEquals(field, (Number) value);
            case EQUALS -> ProductSpecification.equals(field, value);
        };
    }

}
