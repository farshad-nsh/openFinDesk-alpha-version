package com.farshad.openFinDesk.domain.aggregates;

import com.farshad.openFinDesk.infrastructure.annotations.AggregateIdentifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Interface that describes an aggregate.
 *  each aggregate must have one aggregateRoot and one or more entities and valueObjects
 */
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Entity
@MappedSuperclass
@DiscriminatorColumn(name="agg_TYPE")
public abstract class AggregateRoot  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String aggregateType;

    protected String microserviceName;

    protected long version;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public AggregateRoot() {
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
    }

}

