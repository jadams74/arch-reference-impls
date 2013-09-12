package com.j2911;

import com.jayway.restassured.RestAssured;
import org.junit.Before;


/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 9/8/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class FunctionalTest {
    protected static int port = 9000;

    @Before
    public void before(){
        //if(!baseUrl.isEmpty()){
        //    RestAssured.baseURI = baseUrl;
        //}

        RestAssured.port = port;
    }
}
