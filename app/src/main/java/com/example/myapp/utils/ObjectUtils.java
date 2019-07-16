package com.example.myapp.utils;

/*
 * created by taofu on 2019-06-30
 **/
public class ObjectUtils {

    public static <T> T requireNonNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
