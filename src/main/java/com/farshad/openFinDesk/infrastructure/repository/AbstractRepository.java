package com.farshad.openFinDesk.infrastructure.repository;


import com.farshad.openFinDesk.domain.aggregates.AggregateRoot;

public abstract class AbstractRepository<A extends AggregateRoot>
{
   public abstract A doLoad(A aggregate,Long id, Long expectedVersion) throws ClassNotFoundException;

   public abstract void  doSave(A aggregate);

   public abstract A doMerge(A aggregate);

   public abstract void doDelete(A aggregate);

}
