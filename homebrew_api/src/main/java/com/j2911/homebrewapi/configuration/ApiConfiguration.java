package com.j2911.homebrewapi.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 10:48 AM
 * TStoring configuration for the api
 */
public class ApiConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String databaseUser;

    @NotEmpty
    @JsonProperty
    private String databasePassword;

    @NotEmpty
    @JsonProperty
    private String databaseServerAddress;

    @NotEmpty
    @JsonProperty
    private String databasePort;

    @NotEmpty
    @JsonProperty
    private String databaseName;

    @NotEmpty
    @JsonProperty
    private String recipesCollectionName;

    public ApiConfiguration(){}

    public ApiConfiguration(String dbUser, String dbPassword, String dbServerAddress, String dbPort, String dbName,
                            String recipesCollectionName){
        this.databaseUser = dbUser;
        this.databasePassword = dbPassword;
        this.databaseServerAddress = dbServerAddress;
        this.databasePort = dbPort;
        this.databaseName = dbName;
        this.recipesCollectionName = recipesCollectionName;
    }

    public String getDatabaseUser(){
        return this.databaseUser;
    }

    public String getDatabasePassword(){
        return this.databasePassword;
    }

    public String getDatabaseServerAddress(){
        return this.databaseServerAddress;
    }

    public String getDatabasePort(){
        return this.databasePort;
    }

    public String getDatabaseName(){
        return this.databaseName;
    }

    public String getRecipesCollectionName(){
        return this.recipesCollectionName;
    }
}
