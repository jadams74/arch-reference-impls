package com.j2911.homebrewapi.resource;

import org.junit.*;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 9/6/13
 * Time: 7:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeResourceTest {
    @Test
    public void getRecipesOptionsTest(){
        RecipeResource resource = new RecipeResource();

        Response response = resource.getRecipesOptions();
        MultivaluedMap<String, Object> map = response.getMetadata();

        Assert.assertNotNull(map);
        String result = (String)map.get("Access-Control-Allow-Origin").get(0);
        Assert.assertEquals("*", result);

        result = (String)map.get("Access-Control-Allow-Headers").get(0);
        Assert.assertEquals("origin, content-type, accept, authorization, x-requested-with", result);

        result = (String)map.get("Access-Control-Allow-Credentials").get(0);
        Assert.assertEquals("true", result);

        result = (String)map.get("Access-Control-Allow-Methods").get(0);
        Assert.assertEquals("GET, POST, PUT, DELETE, OPTIONS, HEAD", result);
    }
}
