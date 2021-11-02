package com.jiuye.utils;

public class fileNameUtils {
    public static String subFileName(String filename){
        return filename.substring(filename.lastIndexOf("/") + 1);
    }
}
