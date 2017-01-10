package com.j2911.homebrewapi.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.j2911.homebrewapi.db.HomebrewDao;
import com.j2911.homebrewapi.core.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**

 */
@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource extends HomebrewResource {
    private static Logger logger = LoggerFactory.getLogger(RecipeResource.class);
    private HomebrewDao homebrewDao;
    public RecipeResource(){}

    public RecipeResource(HomebrewDao dao){
        this.homebrewDao = dao;
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
    public Response getRecipes(@QueryParam("limit") @DefaultValue("20") int limit,
                               @QueryParam("offset") @DefaultValue("0") int offset){

        List<Recipe> recipes = homebrewDao.findAll(limit, offset);
        return buildResponse(Response.Status.OK, recipes);
    }

    /**
     * Fetch a single recipe
     * @param id
     * @return
     */
    @GET
    @Timed
    @Path("/{id}")
    public Response getRecipeById(@PathParam("id") long id){

        // TODO: Handle 404.
        Response response;

        Recipe recipe = homebrewDao.findById(id);
        if(recipe == null){
            // TODO: Return an error message type as the entity.
            response = buildResponse(Response.Status.NOT_FOUND);
        }else{
            response = buildResponse(Response.Status.OK, homebrewDao.findById(id));
        }
        return response;
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public Response deleteRecipeById(@PathParam("id") long id){
        Response response;

        Recipe recipe = homebrewDao.findById(id);
        if(recipe != null){
            homebrewDao.deleteById(id);
            // TODO: better way to handle cors?
            response = buildResponse(Response.Status.NO_CONTENT);
        }else{
            response = buildResponse(Response.Status.NOT_FOUND);
        }

        return response;
    }
}
