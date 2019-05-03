package task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        /* Создание массива слов initialWords размерностью <=1000 для генерирования предложений */
        URL givenLink = new URL("https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-usa-no-swears-medium.txt");
        BufferedReader inFirst = new BufferedReader(new InputStreamReader(givenLink.openStream()));
        List<String> initialWords = new ArrayList<>();
        String inputLineFirst;
        for (int i = 0; i<1000; i++) {
            inputLineFirst = inFirst.readLine();
            initialWords.add(inputLineFirst);
        }
        inFirst.close();
        System.out.println("Массив слов initialWords размерностью " + initialWords.size() + ": " + initialWords);

        /* Создание массива слов words размерностью <=1000 для реализации вероятности */
        URL givenLinkWords = new URL("https://gist.githubusercontent.com/deekayen/4148741/raw/01c6252ccc5b5fb307c1bb899c95989a8a284616/1-1000.txt");
        BufferedReader inSecond = new BufferedReader(new InputStreamReader(givenLinkWords.openStream()));
        List<String> words = new ArrayList<>();
        String inputLineSecond;
        while ((inputLineSecond = inSecond.readLine()) != null) {
            words.add(inputLineSecond);
        }
        inSecond.close();
        System.out.println("Массив слов words размерностью " + words.size() + ": " + words + "\r\n");

        /* Инстанцирование основного класса программы */
        FilesProcessingClass x = new FilesProcessingClass(initialWords, words);

        x.paragraphGenerator(15,20, 0.9);
        //System.out.println(x.paragraph);

        /* Вызов основного метода класса для генерирования файлов */
        String[] array = words.stream().toArray(String[]::new);
        x.getFiles("C:\\Users\\Gavrilov\\IdeaProjects\\part01.lesson06\\src\\task02\\", 3, 3072, 50, array);
    }
}