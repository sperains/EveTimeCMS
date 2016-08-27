package com.evetime.cms.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * Created by Rains
 * on 2016-05-18.
 */
public class JsonUtil {

    public static Map<String,Object> JsonToMap(String jsonStr){
        ObjectMapper mapper = new ObjectMapper();
        try {
            HashMap map = mapper.readValue(jsonStr, HashMap.class);
            return map;
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> List<T> JsonToList(String jsonStr , Class<T[]> cls) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = new ArrayList<T>();
        try {
          list   = Arrays.asList(mapper.readValue(jsonStr,cls));
        } catch (IOException e) {

        }

        return list;
    }

    public static <T> T JsonToObject(String jsonStr, Class<T> valueType) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            return null;

        }
    }

}
