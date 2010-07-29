package com.sitewidesystems.backlog.repository.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * Creator: gerwood
 * Created: 30/05/2009 9:35:02 AM
 */
public class IterationJdbcRepositoryTest {

    ApplicationContext appCon;

    @Before
    public void setup () {
        appCon = new ClassPathXmlApplicationContext("classpath:/test-config.xml");
    }

    @Test
    public void quickTest() {
        
    }

}
