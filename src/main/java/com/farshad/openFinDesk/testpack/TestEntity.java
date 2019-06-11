package com.farshad.openFinDesk.testpack;

import com.farshad.openFinDesk.infrastructure.annotations.AggregateIdentifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class TestEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
