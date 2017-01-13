package com.j2911.homebrewapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
    Base class for all resources.
 */
@SuppressWarnings("unchecked")
public class HomebrewResource {
    private static Logger LOG = LoggerFactory.getLogger(HomebrewResource.class);
    ObjectMapper objectMapper = new ObjectMapper();

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
        LOG.debug("Building response..");
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

    /**
     * Turn a json array into list of strings.
     * @param string
     * @return
     */
    List<String> StringToJsonArray(String string){
        List<String> list = new ArrayList<>();
        try{
            list = objectMapper.readValue(string, List.class);
        }catch(IOException ex){
            LOG.error("Error parsing json.", ex);
        }

        return list;
    }

    /**
     * Turns a list of strings into json array.
     * @param jsonArray
     * @return
     */
    String jsonArrayToString(List<String> jsonArray){
        String result = "";
        try{
            result = objectMapper.writeValueAsString(jsonArray);
        } catch(IOException ex){
            LOG.error("Error parsong json", ex);
        }

        return result;
    }
}
