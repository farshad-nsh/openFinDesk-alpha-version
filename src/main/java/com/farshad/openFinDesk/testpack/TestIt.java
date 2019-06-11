package com.farshad.openFinDesk.testpack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TestIt {

    @PersistenceContext(unitName = "PersistenceContext1")
    EntityManager entityManager;

    @Autowired
    public TestIt(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Autowired
    TestEntity testEntity;

    @Transactional
    public void test(){
        System.out.println("test----");
        testEntity.setDescription("another disc kk");
        System.out.println(testEntity.getId()+" "+testEntity.getDescription());
        //System.out.println(entityManager.getTransaction().isActive());
      //  System.out.println(entityManager.getTransaction().isActive());
          entityManager.persist(testEntity);
        // Criteria criteria = entityManager.unwrap(Session.class).createCriteria(TestEntity.class);
        //System.out.println(criteria.list());

    }
}
