package com.example.trainline.util;

public class StringUtil {
    public static boolean isNull(String value){
        if(value == null){
            return true;
        }
        if("".equals(value.trim())){
            return true;
        }
        return false;
    }
}
