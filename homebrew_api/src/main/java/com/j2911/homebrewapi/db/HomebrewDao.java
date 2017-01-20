package com.j2911.homebrewapi.db;

import com.j2911.homebrewapi.core.Recipe;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface HomebrewDao {

    //TODO: Style belongs in a table
    //TODO: type belongs in a table

    @SqlUpdate("create schema if not exists homebrew;")
    void createSchema();

    /**
     * Create the recipe table.
     */
    @SqlUpdate("create table if not exists homebrew.recipe (" +
            "id BIGSERIAL primary key, " +
            "created_at timestamp, " +
            "updated_at timestamp, " +
            "name varchar(128) NOT NULL, " +
            "description text, " +
            "og numeric(4, 3), " +
            "fg numeric(4, 3), " +
            "ibu smallint, " +
            "srm smallint, " +
            "abv real, " +
            "style varchar(64) NOT NULL, " +
            "recipe_type varchar(64), " +
            "boil_time smallint, " +
            "fermentables jsonb, " +
            "hops jsonb, " +
            "yeast jsonb, " +
            "other_ingredients jsonb);")
    void createRecipeTable();

    /**
     * This will become a performance bottleneck once the data is large. At minimum
     * create an index.
     * @param limit
     * @param offset
     * @return
     */
    @SqlQuery("SELECT * FROM homebrew.recipe LIMIT :limit OFFSET :offset")
    List<Recipe> findAll(@Bind("limit") int limit,
                         @Bind("offset") int offset);

    @SqlQuery("select * from homebrew.recipe where id = :id")
    Recipe findById(@Bind("id") long id);

    @SqlUpdate("delete from homebrew.recipe where id= :id")
    void deleteById(@Bind("id") long id);

    @SqlUpdate("insert into homebrew.recipe (" +
            "created_at," +
            "updatd_at," +
            "name" +
            "description" +
            "og" +
            "fg" +
            "ibu" +
            "srm" +
            "abv" +
            "style" +
            "recipe_type" +
            "boil_time" +
            "fermentables" +
            "hops" +
            "yeast" +
            "other_ingredients" +
            ") values (" +
            ":createdAt," +
            ":updatedAt," +
            "name:" +
            "description:" +
            "og:" +
            "fg:" +
            "ibu:" +
            "srm:" +
            "abv:" +
            "style:" +
            "recipeType:" +
            "boilTime:" +
            "fermentables:" +
            "hops:" +
            "yeast:" +
            "otherIngredients)")
    @GetGeneratedKeys(RecipeMapper.class)
    Recipe insert(@Bind("createdAt") DateTime createdAt,
                @Bind("updatedAt") DateTime updatedAt,
                  @Bind("name") String name,
                  @Bind("description") String description,
                  @Bind("og") float og,
                  @Bind("fg") float fg,
                  @Bind("ibu") short ibu,
                  @Bind("srm") short srm,
                  @Bind("abv") float abv,
                  @Bind("style") String style,
                  @Bind("recipeType") String recipeType,
                  @Bind("boilTime") short boilTime,
                  @BindJsonb("fermentables") String fermentables,
                  @BindJsonb("hops") String hops,
                  @BindJsonb("yeast") String yeast,
                  @BindJsonb("otherIngredients") String otherIngredients
                  );

    @SqlUpdate("UPDATE homebrew.recipe SET " +
            "updated_at=:updatedAt, " +
            "name=:name, " +
            "description=:description, " +
            "og=:og, " +
            "fg=:fg, " +
            "ibu=:ibu, " +
            "srm=:srm, " +
            "abv=:abv, " +
            "style=:style, " +
            "recipe_type=:recipeType," +
            "boil_time=:boilTime, " +
            "fermentables=:fermentables, " +
            "hops=:hops, " +
            "yeast=:yeast, " +
            "other_ingredients:otherIngredients " +
            "WHERE id=:id;")
    @GetGeneratedKeys(RecipeMapper.class)
    Recipe update(@Bind("id") long id,
                  @Bind("updatedAt") DateTime updatedAt,
                  @Bind("name") String name,
                  @Bind("description") String description,
                  @Bind("og") float og,
                  @Bind("fg") float fg,
                  @Bind("ibu") short ibu,
                  @Bind("srm") short srm,
                  @Bind("abv") float abv,
                  @Bind("style") String style,
                  @Bind("recipeType") String recipeType,
                  @Bind("boilTime") short boilTime,
                  @BindJsonb("fermentables") String fermentables,
                  @BindJsonb("hops") String hops,
                  @BindJsonb("yeast") String yeast,
                  @BindJsonb("otherIngredients") String otherIngredients
    );
}
