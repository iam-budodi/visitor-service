package com.sebastian.visitor;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface VisitorMapper {
    Visitor toDomain(VisitorEntity entity);
    VisitorEntity toEntity(Visitor visitor);
}
