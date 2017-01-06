package com.j2911.homebrewapi;

import com.j2911.homebrewapi.db.HomebrewDao;
import com.j2911.homebrewapi.resource.RecipeResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class HomebrewApiApplication extends Application<HomebrewApiConfiguration> {
    public static void main(final String[] args) throws Exception {
        new HomebrewApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "IPA-MockDelivery";
    }

    @Override
    public void initialize(final Bootstrap<HomebrewApiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HomebrewApiConfiguration configuration,
                    final Environment environment) {

        final DBIFactory dbiFactory = new DBIFactory();
        final DBI dbi = dbiFactory.build(environment, configuration.getDataSourceFactory(), "MockDeliveryResult");
        final HomebrewDao dao = dbi.onDemand(HomebrewDao.class);

        dao.createSchema();
        dao.createRecipeTable();

        environment.jersey().register(new RecipeResource(dao));
    }
}
