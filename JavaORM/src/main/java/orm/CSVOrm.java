package main.java.orm;

import main.java.utils.CSVReader;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CSVOrm<T> {
    private CSVReader<T> csvReader;
    private String filePath;
    private Class<T> type;

    public CSVOrm(String filePath, Class<T> type) {
        this.csvReader = new CSVReader<>(filePath, type);
        this.filePath = filePath;
        this.type = type;
    }

    public List<T> getAll() {
        return csvReader.readAll();
    }
    
    public T findById(int id) {
        List<T> allInstances = getAll();
        for (T instance : allInstances) {
            try {
                Field idField = type.getDeclaredField("id"); // Supone que el campo es `id`
                idField.setAccessible(true);
                if (idField.getInt(instance) == id) {
                    return instance;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null; // Si no se encuentra la instancia
    }

    public void save(T instance) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true para añadir al final del archivo
            StringBuilder line = new StringBuilder();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                line.append(field.get(instance)).append(",");
            }
            line.deleteCharAt(line.length() - 1); // Eliminar la última coma
            writer.write(line.toString() + "\n");
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, T updatedInstance) {
        List<T> allInstances = getAll();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (T instance : allInstances) {
                Field idField = type.getDeclaredField("id");
                idField.setAccessible(true);
                if (idField.getInt(instance) == id) {
                    instance = updatedInstance;
                }
                StringBuilder line = new StringBuilder();
                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    line.append(field.get(instance)).append(",");
                }
                line.deleteCharAt(line.length() - 1);
                writer.write(line.toString() + "\n");
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        List<T> allInstances = getAll();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (T instance : allInstances) {
                Field idField = type.getDeclaredField("id");
                idField.setAccessible(true);
                if (idField.getInt(instance) != id) {
                    // Si el ID no coincide, escribimos la instancia
                    StringBuilder line = new StringBuilder();
                    Field[] fields = type.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        line.append(field.get(instance)).append(",");
                    }
                    line.deleteCharAt(line.length() - 1);
                    writer.write(line.toString() + "\n");
                }
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
}
