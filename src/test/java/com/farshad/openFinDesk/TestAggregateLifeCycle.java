package com.farshad.openFinDesk;


import com.farshad.openFinDesk.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class TestAggregateLifeCycle {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void checkThreadLocalForAggregateLifecycle() throws ClassNotFoundException {

        // given

        // when

        // then


    }
}
