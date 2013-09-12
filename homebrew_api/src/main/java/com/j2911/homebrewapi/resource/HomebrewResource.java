package com.j2911.homebrewapi.resource;

import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/18/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class HomebrewResource {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(HomebrewResource.class);

    protected Response buildResponse(Response.Status status){
        return buildResponse(status, null);
    }

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
