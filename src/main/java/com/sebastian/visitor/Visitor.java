package com.sebastian.visitor;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.NaturalId;

import java.util.Objects;

@Entity
@Table(name = "visitors",
        uniqueConstraints = {
        @UniqueConstraint(name = "visitor_id_uq", columnNames = {"id_type", "id_number"})
})
public class Visitor extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitorSq")
    @SequenceGenerator(name = "visitorSq", sequenceName = "visitor_id_sq", allocationSize = 1, initialValue = 1)
    @Column(name = "visitor_id")
    public Long id;

    public Name name;

    @Basic(optional = false)
    @Column(name = "phone_number", length = 13)
    @Check(name = "valid_phone_no", constraints = "length(phone)=13")
    public String phone;

    @Column(length = 100)
    @Basic(optional = false)
    public String email;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "id_type")
    public IdType idType;

    @NaturalId
    @Basic(optional = false)
    @Column(name = "id_number", length = 25)
    @Check(name = "valid_id_number", constraints = "(id_type = 'NATIONAL_ID' AND length(id_number) = 20) OR " +
            "(id_type = 'DRIVER_LICENSE' AND length(id_number) = 10) OR " +
            "(id_type = 'PASSPORT' AND length(id_number) = 9)")
    public String idNumber;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Visitor visitor)) return false;
        return Objects.equals(idNumber, visitor.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idNumber);
    }
}
