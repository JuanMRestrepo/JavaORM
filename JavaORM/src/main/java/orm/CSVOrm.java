package main.java.orm;

import main.java.utils.CSVReader;
import java.util.List;

public class CSVOrm<T> {
    private CSVReader<T> csvReader;

    public CSVOrm(String filePath, Class<T> type) {
        this.csvReader = new CSVReader<>(filePath, type);
    }

    public List<T> getAll() {
        return csvReader.readAll();
    }

}
