package io.github.bzdgn.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class FilterRequest {
    @NonNull
    private String field;
    @NonNull
    private String operator; // Operators like contains, not_contains, greater_than, etc.
    @NonNull
    private Object value;
}
