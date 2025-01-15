package com.sebastian.visitor;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Visitor")
@Table(
        name = "visitors",
        uniqueConstraints = {@UniqueConstraint(name = "visitor_id_uq", columnNames = {"id_type", "id_number"})},
        indexes = {@Index(name = "visitor_idx", columnList = "phone_number desc, id_type, id_number desc")} // indexes are better left out for DBAs
)
public class VisitorEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitorSq")
    @SequenceGenerator(name = "visitorSq", sequenceName = "visitor_id_sq", allocationSize = 1, initialValue = 1)
    @Column(name = "visitor_id")
    public Long visitorId;

    public Name name;

    @Basic(optional = false)
    @Column(name = "phone_number", length = 13)
    @Check(name = "valid_phone_no", constraints = "length(phone_number)=13")
    @Pattern(regexp = "^\\+255\\d{9}$", message = "{Visitor.phone.invalid}")
    public String phone;

    @Column(length = 100)
    @Basic(optional = false)
    @Email(message = "{Visitor.email.invalid}")
    public String email;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "id_type")
    @NotEmpty(message = "{Visitor.idType.required}")
    public IdType idType;

    @NaturalId
    @Basic(optional = false)
    @Column(name = "id_number", length = 25)
    @Check(name = "valid_id_number", constraints = "(id_type = 'NATIONAL_ID' AND length(id_number) = 20) OR " +
            "(id_type = 'DRIVER_LICENSE' AND length(id_number) = 10) OR " +
            "(id_type = 'PASSPORT' AND length(id_number) = 9)")
    @NotEmpty(message = "{Visitor.idNumber.required}")
    public String idNumber;

    @Version
    protected LocalDateTime lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VisitorEntity visitor)) return false;
        return Objects.equals(idNumber, visitor.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idNumber);
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + visitorId +
                ", name=" + name +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", idType=" + idType +
                ", idNumber='" + idNumber + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
