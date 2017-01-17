package com.j2911.homebrewapi.db;

import com.j2911.homebrewapi.core.Recipe;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;

import java.util.List;

/**
 * A stub for mockito spies.
 */
public class SpyDao implements HomebrewDao {
    @Override
    public void createSchema() {

    }

    @Override
    public void createRecipeTable() {

    }

    @Override
    public List<Recipe> findAll(@Bind("limit") int limit, @Bind("offset") int offset) {
        return null;
    }

    @Override
    public Recipe findById(@Bind("id") long id) {
        return null;
    }

    @Override
    public void deleteById(@Bind("id") long id) {

    }

    @Override
    public Recipe insert(@Bind("createdAt") DateTime createdAt, @Bind("updatedAt") DateTime updatedAt, @Bind("name") String name, @Bind("description") String description, @Bind("og") float og, @Bind("fg") float fg, @Bind("ibu") short ibu, @Bind("srm") short srm, @Bind("abv") short abv, @Bind("style") String style, @Bind("recipeType") String recipeType, @Bind("boilTime") short boilTime, @BindJsonb("fermentables") String fermentables, @BindJsonb("hops") String hops, @BindJsonb("yeast") String yeast, @BindJsonb("otherIngredients") String otherIngredients) {
        return null;
    }
}
