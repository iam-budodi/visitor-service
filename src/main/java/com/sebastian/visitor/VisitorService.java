package com.sebastian.visitor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class VisitorService {

    @Inject
    VisitorRepository visitorRepository;

    @Inject
    VisitorMapper visitorMapper;

    @Inject
    NameMapper nameMapper;

    public List<Visitor> getVisitors() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Visitor> getVisitor(Long visitorId) {
        return findVisitorById(visitorId)
                .map(visitorMapper::toDomain);
    }

    @Transactional
    public Visitor createVisitor(@Valid Visitor visitor) {
        VisitorEntity visitorEntity = visitorMapper.toEntity(visitor);
        visitorRepository.persist(visitorEntity);
        return visitorMapper.toDomain(visitorEntity);
    }

    @Transactional
    public Visitor updateVisitor(@Valid Visitor visitor) {
        VisitorEntity visitorEntity = findVisitorById(visitor.visitorId()).get();
        visitorEntity.email = visitor.email();
        visitorEntity.idNumber = visitor.idNumber();
        visitorEntity.idType = visitor.idType();
        visitorEntity.phone = visitor.phone();
        visitorEntity.name = nameMapper.toName(visitor.name());
        visitorRepository.persist(visitorEntity);
        return visitorMapper.toDomain(visitorEntity);
    }

    public void deleteVisitor(Long visitorId) {
        visitorRepository.deleteById(visitorId);
    }

    private Optional<VisitorEntity> findVisitorById(Long visitorId) {
        return visitorRepository.findByIdOptional(visitorId);
    }
}
