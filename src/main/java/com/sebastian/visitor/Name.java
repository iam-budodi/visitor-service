package com.sebastian.visitor;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

@Embeddable
public record Name(
        @Basic(optional = false) @Column(name = "first_name", length = 64) @NotEmpty(message = "{Visitor.firstName.required}") String firstName,
        @Column(name = "middle_name", length = 64) String middleName,
        @Basic(optional = false) @Column(name = "last_name", length = 64) @NotEmpty(message = "{Visitor.lastName.required}") String lastName
) {
}
