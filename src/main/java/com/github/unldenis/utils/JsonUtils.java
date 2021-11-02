package com.github.unldenis.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class provides basic/common functionalities to be applied on Java Objects.
 */
public final class JsonUtils {

    private static final Gson GSON = new GsonBuilder()
            //.setPrettyPrinting()
            .create();

    private JsonUtils() {
        throw new UnsupportedOperationException("Instantiation of this class is not permitted in case you are using reflection.");
    }

    public static String serializeObject(final Object object) {
        return GSON.toJson(object);
    }


    public static <T> T deserializeObject(final String string, Class<T> clazz) { return GSON.fromJson(string, clazz); }
}