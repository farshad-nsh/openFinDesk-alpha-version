package com.farshad.openFinDesk.domain.repository;

import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.infrastructure.repository.jpa.GenericJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class InstrumentRepository extends GenericJpaRepository<Instrument> {

    public InstrumentRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional
    @Override
    public Instrument doLoad(Instrument instrument,Long id, Long expectedVersion) throws ClassNotFoundException {
        return super.doLoad(instrument,id,expectedVersion);
    }

    @Transactional
    @Override
    public void doSave(Instrument aggregate) {
          super.doSave(aggregate);
    }

    @Transactional
    @Override
    public Instrument doMerge(Instrument aggregate) {
        return super.doMerge(aggregate);
    }

    @Override
    public void doDelete(Instrument aggregate) {
        super.doDelete(aggregate);
    }
}
