package com.j2911.homebrewapi.resource;

import com.j2911.homebrewapi.core.Recipe;
import com.j2911.homebrewapi.db.HomebrewDao;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

public class RecipeResourceTest {

    private final static HomebrewDao dao = mock(HomebrewDao.class);

    @ClassRule
    public final static ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new RecipeResource(dao))
            .build();

    @Before
    public void before(){}

    @After
    public void after(){
        reset(dao);
    }

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

    @Test
    public void getRecipeById_happyPath(){
        Recipe recipe = new Recipe();
        recipe.setId(1);

        when(dao.findById(1)).thenReturn(recipe);

        Response response = resource.client()
                .target("/recipes/1")
                .request()
                .get(Response.class);

        assertEquals(200, response.getStatus());
        Recipe actual = response.readEntity(Recipe.class);
        assertEquals(recipe, actual);
    }

    @Test
    public void getRecipeById_NotFound(){

    }
}
