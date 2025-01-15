package com.sebastian.visitor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link VisitorEntity}
 */
public record Visitor(
        Long visitorId,
        VisitorName name,
        @Pattern(message = "{Visitor.phone.invalid}", regexp = "^\\+255\\d{9}$") String phone,
        @Email(message = "{Visitor.email.invalid}") String email, IdType idType,
        @NotEmpty(message = "{Visitor.idNumber.required}") String idNumber) implements Serializable {
}