package com.sebastian.visitor;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VisitorRepository implements PanacheRepositoryBase<VisitorEntity, Long> {
}
