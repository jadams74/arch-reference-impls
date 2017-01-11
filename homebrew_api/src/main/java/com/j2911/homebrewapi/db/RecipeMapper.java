package com.j2911.homebrewapi.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2911.homebrewapi.core.Recipe;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremiah on 1/11/17.
 */
@SuppressWarnings("unchecked")
public class RecipeMapper implements ResultSetMapper<Recipe> {
    private static Logger LOG = LoggerFactory.getLogger(RecipeMapper.class);
    private ObjectMapper mapper = new ObjectMapper();

    public Recipe map(int index, ResultSet resultSet, StatementContext statmentContext) throws SQLException {
        return new Recipe(
                resultSet.getLong("id"),
                new DateTime(resultSet.getLong("created_at")).toString(),
                new DateTime(resultSet.getLong("updated_at")).toString(),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("og"),
                resultSet.getDouble("fg"),
                resultSet.getInt("ibu"),
                resultSet.getInt("srm"),
                resultSet.getDouble("abv"),
                resultSet.getString("style"),
                resultSet.getString("recipe_type"),
                resultSet.getInt("boil_time"),
                stringToList(resultSet.getString("fermentables")),
                stringToList(resultSet.getString("hops")),
                stringToList(resultSet.getString("yeast")),
                stringToList(resultSet.getString("other_ingredients"))
        );
    }

    private List<String> stringToList(String input){
        List<String>result = new ArrayList<>();
        try{
            result = mapper.readValue(input, List.class);
        }catch(IOException ioex){
            LOG.error("ERROR deserializing object", ioex);
        }

        return result;
    }
}
