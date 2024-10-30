package main.java.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CSVReader<T> {
    private String filePath;
    private Class<T> type;

    public CSVReader(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.type = type;
    }

    public List<T> readAll() {
        List<T> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                T instance = type.getDeclaredConstructor().newInstance();
                String[] values = line.split(",");
                Field[] fields = type.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    fields[i].set(instance, castValue(fields[i].getType(), values[i]));
                }
                data.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private Object castValue(Class<?> fieldType, String value) {
        if (fieldType == int.class) return Integer.parseInt(value);
        if (fieldType == String.class) return value;
        return null;
    }
}
