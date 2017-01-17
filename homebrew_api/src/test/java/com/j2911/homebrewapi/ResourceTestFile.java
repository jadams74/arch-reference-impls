package com.j2911.homebrewapi;

import org.apache.maven.surefire.shade.org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class ResourceTestFile {

    public static String loadJsonFromResources(String fileName) throws Exception {
        ClassLoader classLoader = ResourceTestFile.class.getClassLoader();
        InputStream stream = classLoader.getResource(fileName).openStream();
        return IOUtils.toString(stream);
    }
}
