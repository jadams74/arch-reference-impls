package com.j2911.homebrewapi.db;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;
import java.sql.SQLException;
import org.postgresql.util.PGobject;

/**
 * Created by jeremiah on 12/20/16.
 *
 *  * shamelessly pilfered from http://blog.anorakgirl.co.uk/2016/01/using-jdbi-with-postgres-json-data/
 */
@BindingAnnotation(BindJsonb.JsonbBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindJsonb {
    String value();

    public static class JsonbBinderFactory implements BinderFactory {
        @Override
        public Binder build(Annotation annotation){
            return new Binder<BindJsonb, String>() {
                @Override
                public void bind(SQLStatement q, BindJsonb bind, String jsonString) {
                    try {
                        PGobject data = new PGobject();
                        data.setType("jsonb");
                        data.setValue(jsonString);
                        q.bind(bind.value(), data);
                    } catch (SQLException ex) {
                        throw new IllegalStateException("Error Binding JSON",ex);
                    }
                }
            };

        }
    }
}
