package utils;

import com.google.gson.Gson;

public class Util {

    public static <T> T jsonToObjet(String json, Class<T> classes) {
        Gson gson = new Gson();
        return gson.fromJson(json, classes);
    }

    public static String requestParser(String string){
        return string.replaceAll(" ", "+");
    }
}
