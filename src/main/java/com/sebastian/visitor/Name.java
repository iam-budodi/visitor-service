package com.sebastian.visitor;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Name(
        @Basic(optional=false) @Column(name = "first_name", length = 64) String firstName,
        @Column(name = "middle_name", length = 64) String middleName,
        @Basic(optional=false) @Column(name = "last_name", length = 64) String lastName
) {}
