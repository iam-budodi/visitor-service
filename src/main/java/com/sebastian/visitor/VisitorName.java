package com.sebastian.visitor;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link Name}
 */
public record VisitorName(
        @NotEmpty(message = "{Visitor.firstName.required}") String firstName,
        String middleName,
        @NotEmpty(message = "{Visitor.lastName.required}") String lastName) implements Serializable {
}