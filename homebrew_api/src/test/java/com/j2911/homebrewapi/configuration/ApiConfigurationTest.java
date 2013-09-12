package com.j2911.homebrewapi.configuration;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApiConfigurationTest {
    @Test
    public void ConstructorTest(){
        String dbUser = "user";
        String dbPassword = "pwd";
        String dbServerAddress = "127.0.0.1";
        String dbPort = "1234";
        String dbName = "brewDatabase";
        String recipesCollectionName = "recipes";

        ApiConfiguration config = new ApiConfiguration(dbUser, dbPassword, dbServerAddress, dbPort, dbName, recipesCollectionName);
        Assert.assertEquals(dbUser, config.getDatabaseUser());
        Assert.assertEquals(dbPassword, config.getDatabasePassword());
        Assert.assertEquals(dbServerAddress, config.getDatabaseServerAddress());
        Assert.assertEquals(dbPort, config.getDatabasePort());
        Assert.assertEquals(dbName, config.getDatabaseName());
        Assert.assertEquals(recipesCollectionName, config.getRecipesCollectionName());
    }
}
