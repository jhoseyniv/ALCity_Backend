package com.alcity.utility;

import com.alcity.entity.alenum.ADSType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToolBox {

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static void SendMessageToImportLogs(String message,String Date) throws IOException {
        Path path = Paths.get("C:\\Import-Logs\\Import-PG.txt");
        if(!path.toFile().exists())
            Files.createFile(path);
        else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
            writer.append("\n");
            writer.append(Date + "-----"+ message);
            writer.close();
        }

    }
    public static Class getEnumById(Class object,long id) {
        String fullClassName = object.getName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
//        if (object.isEnum()) {
//            System.out.println(className);
//        }
        switch (className) {
            case "ADSType":
            {
                for (ADSType e : ADSType.values())
                    {
                     if (id == e.ordinal()) return e.getClass();
                 }
                System.out.println("file extension is image  and size is:  " + className);
                break;
            }
        }
        return object;
    }
    public static Integer getPuzzleLevelStars(Float score,Float first,Float two,Float three) {
        if( 0 <= score && score <= first) return  1;
        if( first <= score && score<= two) return  2;
        if(score >= three) return  3;
        return 1;
    }
    public static boolean compareCaseInsensitive(String s1, String s2){
        if(s1.toLowerCase().contains(s2.toLowerCase())) return true;
        return false;
    }

    public static void main(String args[]){
        getEnumById(ADSType.class,1);
        String ref="sandcat,sandcat-moslem1403981,11-04-2025 15:21:50";
        String[] tokens = ref.split(",");
        for(int i=0; i<tokens.length;i++)
        System.out.println(tokens[i]);
    }
}
