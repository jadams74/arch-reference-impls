package com.j2911.homebrewapi.data;

import com.j2911.homebrewapi.configuration.ApiConfiguration;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 11:10 AM
 * MongoDB specific data adapter. Manages connectivity to a MongoDB.
 */
public class MongoDataAdapter implements IDataAdapter {
    private static Logger logger = LoggerFactory.getLogger(MongoDataAdapter.class);
    protected Mongo mongo = null;
    protected DB db  = null;
    protected ApiConfiguration configuration;

    public MongoDataAdapter(ApiConfiguration config){
        configuration = config;
        logger.debug(String.format("DataAdapter Configuration: %s", configuration.toString()));
    }

    /***
     * Connects to the monogdb
     * @return false if some kind of exception.
     */
    @Override
    public boolean connect() throws DatabaseException{
        boolean success = true;

        try{
            // this opens the connection
            logger.info(String.format("Connecting to MogdoDB at %s:%s", this.configuration.getDatabaseServerAddress()
                    , this.configuration.getDatabasePort()));
            int port = Integer.parseInt(this.configuration.getDatabasePort());
            MongoClient client =  new MongoClient(this.configuration.getDatabaseServerAddress(), port);

            // mongo will create the database if it doesn't exist.
            logger.info(String.format("Fetching Database %s", this.configuration.getDatabaseName()));
            db = client.getDB(this.configuration.getDatabaseName());
            boolean authed = db.authenticate(this.configuration.getDatabaseUser(), this.configuration.getDatabasePassword().toCharArray());
            if(!authed){
                throw new DatabaseException("MongoDB Authentication failed.");
            }
        }
        catch(Exception ex){
            success = false;
            logger.error("Could not connect to MongoDB", ex);
            throw new DatabaseException(ex.getMessage());
        }
        return success;
    }

    @Override
    public boolean close(){
        boolean  success = true;
        try{
            logger.info("Closing database connection.");
            if(this.mongo != null){
                mongo.close();
            }
        }
        catch(Exception ex){
            success = false;
            logger.error("Could not close connection to mongoDb");
        }
        return success;
    }
}
