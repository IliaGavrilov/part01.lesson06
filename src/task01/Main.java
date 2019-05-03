package task01;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        /* Открытие файла */
        File file = new File("C:\\Users\\Gavrilov\\IdeaProjects\\part01.lesson06\\src\\task01\\FileToRead.txt");

        /* Инстанцирование объекта класса FileProcessingClass и передача в конструктор файла */
        FileProcessingClass processingFile = new FileProcessingClass(file);

        /* Вызов метода чтения файла  */
        processingFile.readFileString();
        System.out.println(processingFile.wordsArray);

        /* Вызов метода для удаления повторяемых слов */
        processingFile.dropDuplicates();
        System.out.println(processingFile.wordsArray);

        /* Вызов метода сортировки списка слов по алфавиту */
        processingFile.arraySorting();
        System.out.println(processingFile.wordsArray);

        /* Вызов метода сохранения списка слов в файл-результат */
        processingFile.writeFile();
    }
}
