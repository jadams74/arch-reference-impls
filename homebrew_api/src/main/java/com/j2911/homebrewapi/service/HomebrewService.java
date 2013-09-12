package com.j2911.homebrewapi.service;

import com.j2911.homebrewapi.configuration.ApiConfiguration;
import com.j2911.homebrewapi.data.IRecipeDataAdapter;
import com.j2911.homebrewapi.data.RecipeDataAdapter;
import com.j2911.homebrewapi.resource.RecipeResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/18/13
 * Time: 10:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class HomebrewService extends Service<ApiConfiguration>{
    private static Logger logger = LoggerFactory.getLogger(HomebrewService.class);

    public static void main(String[] args) throws Exception{
        new HomebrewService().run(args);    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootStrap){
        bootStrap.setName("recipes");
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment){
        logger.debug("Initializing Homebrew service...");
        try{
            IRecipeDataAdapter recipeDataAdapter = new RecipeDataAdapter(configuration);
            logger.debug("adding RecipeResource to environment...");
            environment.addResource(new RecipeResource(recipeDataAdapter));
            logger.debug("adding health checks to environment...");
            //environment.addHealthCheck(new MongoDbHealthCheck(userDataAdapter));
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }
}