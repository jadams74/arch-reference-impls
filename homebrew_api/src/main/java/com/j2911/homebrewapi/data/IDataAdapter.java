package com.j2911.homebrewapi.data;

/**
 * Created with IntelliJ IDEA.
 * User: jadams
 * Date: 8/10/13
 * Time: 11:00 AM
 */
public interface IDataAdapter {
    boolean connect() throws DatabaseException;

    boolean close();
}
