package com.alcity.utility;

import com.alcity.entity.alenum.ADSType;
import com.alcity.entity.puzzle.ALCityObject;

import java.io.File;
import java.util.Optional;

public class ToolBox {
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
//    public static ADSType getByTitle(String title)
//    {
//        for (ADSType e : ADSType.values())
//        {
//            if (title.equalsIgnoreCase(e.name())) return e;
//        }
//        throw new IllegalArgumentException("no");
//    }
    public static void main(String args[]){
        getEnumById(ADSType.class,1);
    }
}
