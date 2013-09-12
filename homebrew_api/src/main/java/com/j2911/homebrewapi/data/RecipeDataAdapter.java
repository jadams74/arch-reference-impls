package com.j2911.homebrewapi.data;

import com.j2911.homebrewapi.configuration.ApiConfiguration;
import com.j2911.homebrewapi.domain.Recipe;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.util.JSON;


/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 11:15 AM
 * Crud operations on Recipe(s)
 */
public class RecipeDataAdapter extends MongoDataAdapter implements IRecipeDataAdapter{
    private static Logger logger = LoggerFactory.getLogger(RecipeDataAdapter.class);

    public RecipeDataAdapter(ApiConfiguration configuration){
        super(configuration);
    }

    @Override
    public boolean store(Recipe recipe) throws DatabaseException {
        boolean success = false;
        logger.debug(String.format("Beginning Create Recipe: %s", recipe.toString()));

        try{
            if(this.connect()){
                // mongo will create this if it doesn't exist.
                logger.debug(String.format("Creating Recipe:\n\n%s", recipe));
                DBCollection collection = db.getCollection(this.configuration.getRecipesCollectionName());
                logger.debug(String.format("Using Collection: %s", configuration.getRecipesCollectionName()));
                BasicDBObject dbObject = this.createDBOjbectFromRecipe(recipe);

                logger.debug(String.format("Writing Document: \n%s", dbObject.toString()));
                WriteResult writeResult = collection.insert(WriteConcern.SAFE, dbObject);
                String error = writeResult.getError();

                if(error == null || error.isEmpty()){
                    success = true;
                    logger.debug(String.format("Recipe Written to databse: %s", recipe));
               }
            }
        }
        catch(MongoException mex){
            logger.debug(mex.getMessage());
        }
        catch (Exception ex){
            logger.error(String.format("Error creating Recipe: %s\n\n%s", recipe.toString(), ex.toString()));
            throw new DatabaseException(ex.getMessage());
        }
        finally{
            this.close();
        }
        return success;
    }

    @Override
    public boolean deleteRecipe(Recipe recipe) throws DatabaseException {
        boolean success = false;
        logger.debug(String.format("Attempting to Delete Recipe:\n\n%s", recipe.getName()));

        try{
            if(this.connect()){
                BasicDBObject query = this.createDBOjbectFromRecipe(recipe);
                DBCollection collection = db.getCollection(this.configuration.getRecipesCollectionName());
                DBObject dbObject = collection.findOne(query);

                if(dbObject != null){
                    collection.remove(dbObject);
                    success = true;
                }
            }
        }
        catch(Exception ex){
            logger.error(String.format("Error deleting Recipe.\n\n%s\n\n%s", recipe.getName(), ex.toString()));
            throw new DatabaseException(ex.getMessage());
        }
        finally{
            this.close();
        }

        logger.debug(String.format("Recipe %s deleted.", recipe.getName()));
        return success;
    }

    @Override
    public List<Recipe> getRecipes() throws DatabaseException {
        logger.debug("fetching recipes");
        List<Recipe>recipes = new ArrayList<Recipe>();
        try{
            if(this.connect()){
                DBCollection collection = db.getCollection(this.configuration.getRecipesCollectionName());
                //collection.setObjectClass(Recipe.class);
                DBCursor cursor = collection.find();
                try {
                    while(cursor.hasNext()) {
                        DBObject recipeObj = cursor.next();
                        if(recipeObj != null){
                            Recipe recipe = new Recipe();
                            recipe.setName((String)recipeObj.get("Name"));
                            recipe.setDescription((String)recipeObj.get("Description"));
                            recipe.setOriginalGravity((String)recipeObj.get("OriginalGravity"));
                            recipe.setFinalGravity((String)recipeObj.get("FinalGravity"));
                            recipe.setInternationalBitternessUnits((String)recipeObj.get("InternationalBitternessUnits"));
                            recipe.setStandardReferenceMethod((String)recipeObj.get("StandardReferenceMethod"));
                            recipe.setAlcoholByVolume((String)recipeObj.get("AlcoholByVolume"));
                            recipe.setStyle((String)recipeObj.get("Style"));
                            recipe.setRecipeType((String)recipeObj.get("RecipeType"));
                            recipe.setBoilTime((String)recipeObj.get("BoilTime"));
                            recipe.setFermentables((List<String>)recipeObj.get("Fermentables"));
                            recipe.setHops((List<String>)recipeObj.get("Hops"));
                            recipe.setYeast((List<String>)recipeObj.get("Yeast"));
                            recipe.setOtherIngredients((List<String>)recipeObj.get("OtherIngredients"));

                            recipes.add(recipe);
                        }
                    }
                } finally {
                    cursor.close();
                }
            }
        }
        catch (Exception ex){
            logger.error(String.format("Error fetching Recipes: \n%s", ex.toString()));
             throw new DatabaseException(ex.getMessage());
        }
        finally{
            this.close();
        }
        return recipes;
    }

    private BasicDBObject createDBOjbectFromRecipe(Recipe recipe){
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("Name", recipe.getName());
        dbObject.put("Description", recipe.getDescription());
        dbObject.put("OriginalGravity", recipe.getOriginalGravity());
        dbObject.put("FinalGravity", recipe.getFinalGravity());
        dbObject.put("InternationalBitternessUnits", recipe.getInternationalBitternessUnits());
        dbObject.put("StandardReferenceMethod", recipe.getStandardReferenceMethod());
        dbObject.put("AlcoholByVolume", recipe.getAlcoholByVolume());
        dbObject.put("Style", recipe.getStyle());
        dbObject.put("RecipeType", recipe.getRecipeType());
        dbObject.put("BoilTime", recipe.getBoilTime());
        dbObject.put("Fermentables", recipe.getFermentables());
        dbObject.put("Hops", recipe.getHops());
        dbObject.put("Yeast", recipe.getYeast());
        dbObject.put("OtherIngredients", recipe.getOtherIngredients());
        return dbObject;
    }
}
