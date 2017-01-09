package com.j2911.homebrewapi.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
    Base class for all resources.
 */
public class HomebrewResource {
    private static Logger logger = LoggerFactory.getLogger(HomebrewResource.class);

    protected Response buildResponse(Response.Status status){
        return buildResponse(status, null);
    }

    /**
     * Populates the headers w/ cors headers
     * TODO: Is there a better way to handle cors now?
     * TODO: Error message type.
     * @param status
     * @param entity
     * @return
     */
    protected Response buildResponse(Response.Status status, Object entity){
        logger.debug("Building response..");
        Response.ResponseBuilder builder = null;

        if(entity == null){
            builder = Response.status(status);
        }
        else{
            builder = Response.status(status).entity(entity);
        }

        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-requested-with");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        return builder.build();
    }
}
