package com.farshad.openFinDesk.infrastructure.repository.jpa;

import com.farshad.openFinDesk.domain.aggregates.AggregateRoot;
import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.infrastructure.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericJpaRepository<A extends AggregateRoot>  extends AbstractRepository<A> {

    @PersistenceContext(unitName = "PersistenceContext1")
    private EntityManager entityManager;


    @Autowired
    public  GenericJpaRepository(EntityManager entityManager) {
        this.entityManager=entityManager;
    }


    @Transactional
    @Override
    public A doLoad(A aggregate,Long id, Long expectedVersion) throws ClassNotFoundException {
        return (A) entityManager.find(aggregate.getClass(),id);
    }

    @Transactional
    @Override
    public void doSave(A aggregate) {
      entityManager.persist(aggregate);
    }

    @Transactional
    @Override
    public A doMerge(A aggregate) {
        return entityManager.merge(aggregate);
    }

    @Transactional
    @Override
    public void doDelete(A aggregate) {
       entityManager.remove(aggregate);
    }
}
