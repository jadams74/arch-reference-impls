package com.j2911.homebrewapi.data;

import com.j2911.homebrewapi.configuration.ApiConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/11/13
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class MongoTestBase {
    protected ApiConfiguration config = null;

    public MongoTestBase(){
        config = new ApiConfiguration("jadams", "pUk3sta1n", "127.0.0.1", "27017", "homeBrewDb", "recipes");
        //config = new SebsConfiguration("jadams", "", "linus.mongohq.com", "10041", "app12274411", "users", "households");
    }
}
