package com.j2911.homebrewapi.resource;

import com.j2911.homebrewapi.data.DatabaseException;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import com.j2911.homebrewapi.data.IRecipeDataAdapter;
import com.j2911.homebrewapi.domain.Recipe;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/18/13
 * Time: 9:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource extends HomebrewResource {
    private static Logger logger = LoggerFactory.getLogger(RecipeResource.class);
    private IRecipeDataAdapter recipeAdapter = null;

    public RecipeResource(){}

    public RecipeResource(IRecipeDataAdapter recipeAdapter){
        this.recipeAdapter = recipeAdapter;
    }

    @OPTIONS
    @Path("/testData")
    @Timed
    public Response getTestDataOptions(){
        Response.ResponseBuilder builder = Response.noContent();
        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-requested-with");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        return builder.build();
    }

    @GET
    @Path("/testData")
    @Timed
    public Response getTestData(){
        Recipe recipe = new Recipe();

        String name = "Belgian Dark Strong Ale";
        String description = "A dark, strong and malty belgian ale.  Similar to Chimay Blue Label, this is an intermediate" +
                "style that will require a starter or multiple yeast packages. ";
        String originalGravity = "1.089";
        String finalGravity = "1.019";
        String internationalBitternessUnits = "34";
        String standardReferenceMethod = "22";
        String alcoholByVolume = "9.5";
        String style = "Belgian Dark";
        String recipeType = "Extract";
        String boilTime = "60";

        List<String> fermentables = new ArrayList<String>();
        fermentables.add("7 lbs Pilsner liquid malt extract");
        fermentables.add("2 lbs Munich liquid malt extract");

        List<String> hops = new ArrayList<String>();
        hops.add("1.7 oz Mt. Hood (60 min)");

        List<String> yeast = new ArrayList<String>();
        yeast.add("WLP500");
        yeast.add("WYEAST 1762");
        yeast.add("Safebrew T-54");

        List<String> otherIngredients = new ArrayList<String>();

        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setOriginalGravity(originalGravity);
        recipe.setFinalGravity(finalGravity);
        recipe.setInternationalBitternessUnits(internationalBitternessUnits);
        recipe.setStandardReferenceMethod(standardReferenceMethod);
        recipe.setAlcoholByVolume(alcoholByVolume);
        recipe.setStyle(style);
        recipe.setRecipeType(recipeType);
        recipe.setBoilTime(boilTime);
        recipe.setFermentables(fermentables);
        recipe.setHops(hops);
        recipe.setYeast(yeast);
        recipe.setOtherIngredients(otherIngredients);

        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.setName("Coal Train Porter");
        recipe.setAlcoholByVolume("5.75");
        recipe.setBoilTime("1 hour");
        recipe.setDescription("Named by a local Pastor for the train that keeps the Castle Rock residents " +
                "awake on cool summer nights, this porter is a smooth drinker.  This porter is " +
                "unique in using Blackprinz malt, a debittered black malt which provides a " +
                "soft roasted flavor without the bitterness and astringency.  The flavors will " +
                "be both mellow, smooth and very complex.");

        fermentables = new ArrayList<String>();
        fermentables.add("8 lbs 8 oz Marris Otter");
        fermentables.add("2 lbs Munich I");
        fermentables.add("6 oz Special B Malt");
        fermentables.add("6 oz Crystal 40 Malt");
        fermentables.add("4 oz Chocolate Malt");
        fermentables.add("8 oz Blackprinz Malt");
        recipe.setFermentables(fermentables);

        recipe.setStyle("Porter");
        recipe.setRecipeType("All Grain");
        recipe.setFinalGravity("1.058");
        hops = new ArrayList<String>();
        hops.add("1.5 oz Kent Golding (60 min)");
        hops.add("0.75 oz Kent Golding (5 min)");
        recipe.setHops(hops);

        recipe.setInternationalBitternessUnits("39");
        recipe.setOriginalGravity("1.058");
        recipe.setStandardReferenceMethod("39");


        yeast = new ArrayList<String>();
        yeast.add("WYEAST 1335");
        yeast.add("WYEAST 1028");
        yeast.add("WYEAST 1056");
        yeast.add("WYEAST 1275");
        yeast.add("WYEAST 1450");
        yeast.add("Dry S-04");

        recipe.setYeast(yeast);
        recipes.add(recipe);

        Response response = buildResponse(Response.Status.OK, recipes);
        return response;
    }

    @OPTIONS
    @Timed
    public Response getRecipesOptions(){
        Response.ResponseBuilder builder = Response.noContent();
        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-requested-with");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        return builder.build();
    }

    @GET
    @Timed
    public Response getRecipes(){
        Response response = null;

        try{
            List<Recipe> recipes = recipeAdapter.getRecipes();
            if(recipes == null){
                response = buildResponse(Response.Status.NOT_FOUND);
            }
            else{
                response = buildResponse(Response.Status.OK, recipes);
            }
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
            response = buildResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
