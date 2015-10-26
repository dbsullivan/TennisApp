package com.TennisApp.java;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dave on 10/26/2015.
 */
public class ApplicationStartupTest {

    @Test
    public void testInit() throws Exception {

    }

    @Test
    public void testDoGet() throws Exception {

    }

    @ClassRule
    public static TemporaryFolder temporaryFolder = new TemporaryFolder();
    @Before
    public void setUp() throws IOException {
        temporaryFolder.create();
    }

    @Test
    public void testLoadProperties() throws Exception {
        String propsPath = "com/TennisApp/java/tennisApp.properties";
        File propertiesFilePath = temporaryFolder.newFile("propsPath");
        // ASK - object created vs file?
//        properties = new Properties();
//        properties.load(propertiesFilePath);
//        assertNotNull(properties);
    }
}