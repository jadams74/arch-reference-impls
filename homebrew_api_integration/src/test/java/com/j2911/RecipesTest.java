package com.j2911;

import org.junit.*;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import com.jayway.restassured.http.ContentType;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 9/8/13
 * Time: 12:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecipesTest extends FunctionalTest{
    @Test
    public void recipesTest(){

        expect().log().all().
                contentType(ContentType.JSON).
                statusCode(200).
        when().
        given().
        get("/recipes");
    }
}
