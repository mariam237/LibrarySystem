package com.maids.maidstask;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Utils {
    public Object assignObjects(Object oldObject,Object newObject){
        Field[] updatedFields = newObject.getClass().getDeclaredFields();
        for(Field updatedField: updatedFields){
            updatedField.setAccessible(true);
            try {
                Object updatedValue = updatedField.get(newObject);
                // Access the original field in the old data
                if(updatedValue != null){
                    Field originalField = oldObject.getClass().getDeclaredField(updatedField.getName());
                    originalField.setAccessible(true);
                    originalField.set(oldObject, updatedValue);
                }

            } catch (NoSuchFieldException |IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return oldObject;
    }
}
