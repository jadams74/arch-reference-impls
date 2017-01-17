package com.j2911.homebrewapi.resource;

import com.j2911.homebrewapi.ResourceTestFile;
import com.j2911.homebrewapi.core.Recipe;
import com.j2911.homebrewapi.db.HomebrewDao;
import com.j2911.homebrewapi.db.SpyDao;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.maven.surefire.shade.org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
public class RecipeResourceTest {

    private final static HomebrewDao mockDao = mock(HomebrewDao.class);

    @ClassRule
    public final static ResourceTestRule resourceMock = ResourceTestRule.builder()
            .addResource(new RecipeResource(mockDao))
            .build();

    private final static SpyDao spyDaoImpl = new SpyDao();
    private static SpyDao spyDao = spy(spyDaoImpl);
    @ClassRule
    public final static ResourceTestRule resourceSpy = ResourceTestRule.builder()
            .addResource(new RecipeResource(spyDao))
            .build();

    @Before
    public void before(){}

    @After
    public void after(){

        reset(mockDao);
        reset(spyDao);

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

        when(mockDao.findById(1)).thenReturn(recipe);

        Response response = resourceMock.client()
                .target("/recipes/1")
                .request()
                .get(Response.class);

        assertEquals(200, response.getStatus());
        Recipe actual = response.readEntity(Recipe.class);
        assertEquals(recipe, actual);
    }

    @Test
    public void getRecipeById_NotFound(){

        when(mockDao.findById(2)).thenReturn(null);

        Response response = resourceMock.client()
                .target("/recipes/2")
                .request()
                .get(Response.class);

        assertEquals(404, response.getStatus());
    }


    private List<Recipe> buildMockRecipeList(int start, int count){
        List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < count; i++){
            Recipe r = new Recipe();
            r.setId(i + start);
            r.setName(String.format("test%d", i));
            recipes.add(r);
        }

        return recipes;
    }

    /**
     * Perfect example of why integration tests are necessary. Any mocking I do here cannot test this feature.
     * e.g., there are 20 records in the db, but I only want 10.
     */
    @Test
    public void getRecipes_defaults() {
        when(mockDao.findAll(eq(20), eq(0))).thenReturn(buildMockRecipeList(0, 20));

        Response response = resourceMock.client()
                .target("/recipes")
                .request()
                .get(Response.class);

        assertEquals(200, response.getStatus());

        List<Recipe> recipes = response.readEntity(List.class);
        assertEquals(20, recipes.size());
    }

    @Test
    public void getRecipes_paging(){
        when(mockDao.findAll(eq(5), eq(1))).thenReturn(buildMockRecipeList(1, 5));

        Response response = resourceMock.client()
                .target("/recipes?limit=5&offset=1")
                .request()
                .get(Response.class);

        assertEquals(200, response.getStatus());

        List<Map<String, Object>> recipes = response.readEntity(List.class);
        assertEquals(5, recipes.size());
        assertEquals(1, recipes.get(0).get("id"));
    }

    @Test
    public void postRecipe_happyPath() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(100L);

        when(mockDao.insert(
                any(DateTime.class),
                any(DateTime.class),
                anyString(),
                anyString(),
                anyFloat(),
                anyFloat(),
                anyShort(),
                anyShort(),
                anyShort(),
                anyString(),
                anyString(),
                anyShort(),
                anyString(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(recipe);

        String requestBody = ResourceTestFile.loadJsonFromResources("recipe.json");

        Response response = resourceMock.client()
                .target("/recipes")
                .request()
                .post(Entity.entity(requestBody, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(201, response.getStatus());

        String location = response.getHeaders().get("location").get(0).toString();
        assertEquals("http://localhost:9998/recipes/" + recipe.getId(), location);
    }

    /**
     * See the problem here? We use a ResourceTestRule for testing the routes. ResourceTestRule requires a Dao to be passed
     * into the constructor. Delete does not return a value in the dao. Mockito requires us to use a spy on a real implementation
     * of the same Dao interface which is mocked everywhere else. Can't really do that though - create is once. so make a duplicate.
     *
     * Notice that with a stubbed impl, it basically negates the benefit of the mocks used in other methods in the ReciepResource.
     * Docker solves this by removing all mocking for this trivial work.
     *
     */
    @Test
    public void delete_happyPath(){

        // have to use a spy here due to void return type of the method.
        doNothing().when(spyDao).deleteById(anyLong());
        when(spyDao.findById(1L)).thenReturn(new Recipe());

        Response response = resourceSpy.client()
                .target("/recipes/1")
                .request()
                .delete();

        assertEquals(204, response.getStatus());

        verify(spyDao, times(1)).findById(1L);
        verify(spyDao, times(1)).deleteById(1L);
    }

    @Test
    public void delete_notFound() {

        doNothing().when(spyDao).deleteById(1L);
        when(spyDao.findById(1L)).thenReturn(null);

        Response response = resourceSpy.client()
                .target("/recipes/1")
                .request()
                .delete();

        assertEquals(404, response.getStatus());

        verify(spyDao, times(1)).findById(1L);
        verify(spyDao, times(0)).deleteById(1L);
    }
}
