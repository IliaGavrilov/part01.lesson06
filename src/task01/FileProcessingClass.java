package task01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileProcessingClass implements FileProcessingInterface {

    public File processingFile;
    public List<String> wordsArray = new ArrayList<String>();

    public FileProcessingClass (File givenFile) {
        this.processingFile = givenFile;
    }

    /* Метод чтения файла */
    public void readFileString() throws Exception {
        Scanner reader = new Scanner(processingFile);
        List<String> items = new ArrayList<String>();
        String delims = "[ .,?!;–-]+";
        while (reader.hasNextLine()) {
            String line = reader.next();
            items = Arrays.asList(line.split(delims));
            for (String item : items) {
                wordsArray.add(item);}
        }
        reader.close();
    }

    /* Метод для удаления повторяемых слов */
    public void dropDuplicates(){
        Set<String> set = new LinkedHashSet<>();
        set.addAll(wordsArray);
        wordsArray.clear();
        wordsArray.addAll(set);
    }

    /* Метод сортировки списка слов по алфавиту */
    public void arraySorting(){
        Collections.sort(wordsArray);
    }

    /* Метод сохранения списка слов в файл-результат */
    public void writeFile() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\Gavrilov\\IdeaProjects\\part01.lesson06\\src\\task01\\output.txt");
        int size = wordsArray.size();
        int counter = 0;
        for (int i=0;i<size;i++) {
            String str = wordsArray.get(i);//.toString();
            writer.write(str);
            writer.write(" ");
            counter++;
            if (counter==3) {writer.write("\n"); counter=0;}
        }
        writer.close();
    }
}
