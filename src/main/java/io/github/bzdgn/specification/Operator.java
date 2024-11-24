package io.github.bzdgn.specification;

import io.github.bzdgn.exception.InvalidFilterException;

import java.util.Arrays;
import java.util.List;

public enum Operator {
    CONTAINS("contains", "has"),
    NOT_CONTAINS("not_contains", "not contains", "has not"),
    GREATER_THAN("greater_than", "greater than", ">"),
    GREATER_THAN_OR_EQUALS("greater_than_or_equals", "greater than or equals", ">="),
    LESS_THAN("less_than", "less than", "<"),
    LESS_THAN_OR_EQUALS("less_than_or_equals", "less than or equals", "<="),
    EQUALS("equals", "is", "=", "==");

    private final List<String> aliases;

    Operator(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    public List<String> getAliases() {
        return aliases;
    }

    public static Operator fromString(String value) {
        return Arrays.stream(values())
                .filter(op -> op.aliases.contains(value))
                .findFirst()
                .orElseThrow(() -> new InvalidFilterException("Invalid operator: " + value));
    }
}

