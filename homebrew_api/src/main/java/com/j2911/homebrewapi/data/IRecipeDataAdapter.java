package com.j2911.homebrewapi.data;

import com.j2911.homebrewapi.domain.Recipe;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 11:06 AM
 * Crud operations on a recipe(s)
 */
public interface IRecipeDataAdapter extends IDataAdapter{
    boolean store(Recipe recipe) throws DatabaseException;
    boolean deleteRecipe(Recipe recipe) throws DatabaseException;
    List<Recipe> getRecipes() throws DatabaseException;
}
