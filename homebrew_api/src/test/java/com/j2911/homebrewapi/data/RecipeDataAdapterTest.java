package com.j2911.homebrewapi.data;

import com.j2911.homebrewapi.configuration.ApiConfiguration;

import org.junit.*;

// imports for data import
import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

import com.j2911.homebrewapi.domain.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/11/13
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeDataAdapterTest extends MongoTestBase{
    private List<Recipe> recipes = null;
    private RecipeDataAdapter dataAdapter = null;

    @Before
    public void before()throws DatabaseException, IOException{
        dataAdapter = new RecipeDataAdapter(config);
        loadDataFromFile();

        // if no data exists in the database - execute this:
        //        for(Recipe recipe : this.recipes){
        //            this.dataAdapter.deleteRecipe(recipe);
        //        }
    }

    /**
     * Order of delete, store, get ensures that clean data remains in the database after testing.
     * @throws DatabaseException
     */
    @Test
    public void StoreFetchDeleteTest() throws DatabaseException{
        // delete the data.
        // the sequence of these can break tests.
        for(Recipe recipe : this.recipes){
            Assert.assertTrue(this.dataAdapter.deleteRecipe(recipe));
        }

        // insert the data.
        for(Recipe recipe : this.recipes){
            Assert.assertTrue(this.dataAdapter.store(recipe));
        }

        // test the get.
        List<Recipe> tmpRecipes = dataAdapter.getRecipes();
        Assert.assertTrue(tmpRecipes.size() > 0);
        Assert.assertEquals(recipes.get(0), tmpRecipes.get(0));
    }

    /**
     * Loads the data from json file into local list.
     * @throws IOException
     */
    private void loadDataFromFile() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String workingDir = System.getProperty("user.dir");
        String path = workingDir + "/testData.json";
        System.out.println("data file path: " + path);
        Recipe[] tmpRecipes = mapper.readValue(new File(path), Recipe[].class);
        System.out.println(mapper.writeValueAsString(tmpRecipes));

        this.recipes = new ArrayList<Recipe>();
        for(Recipe recipe: tmpRecipes){
            this.recipes.add(recipe);
        }
    }
}
