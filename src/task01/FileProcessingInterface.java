package task01;

import java.io.IOException;

/* Создание интерфейса для учебных целей */
public interface FileProcessingInterface {
    void readFileString() throws Exception;
    void dropDuplicates();
    void arraySorting();
    void writeFile() throws IOException;
}
