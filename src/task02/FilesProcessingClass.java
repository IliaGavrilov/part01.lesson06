package task02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FilesProcessingClass {
    /*Основные и вспомогательные поля класса для генерирования файлов*/
    List<String> words = new ArrayList<>();
    List<String> initialWords = new ArrayList<>();
    String previousSentence = "";
    String paragraph = "";
    Random rand = new Random();
    List<String> commaSpace = new ArrayList<>(Arrays.asList(", ", " ", " "));
    String sentenceEnd =  "(.|!|?)+\"\"";
    String alphabet =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    List<String> previousSentenceWords = new ArrayList<>();
    ArrayList<FileOutputStream> multipleFos = new ArrayList<>();

    /* Конструктор класса */
    public FilesProcessingClass(List<String> initialWords, List<String> words){
        this.initialWords = initialWords;
        this.words = words;
    }

    /* Метод генерирования предложения */
    public void sentenceGenerator(int n){
        String word = "";
        previousSentence = "";
        previousSentenceWords.clear();
        previousSentence = previousSentence + alphabet.charAt(rand.nextInt(alphabet.length())) + " ";
        for (int i=0; i<n-1; i++) {
            word =  initialWords.get(rand.nextInt(initialWords.size()));
            previousSentence = previousSentence + word;
            previousSentence = previousSentence + commaSpace.get(rand.nextInt(commaSpace.size()));
            previousSentenceWords.add(word);
        }
        word = words.get(rand.nextInt(words.size()));
        previousSentence = previousSentence + words.get(rand.nextInt(words.size()));
        previousSentence = previousSentence + sentenceEnd.charAt(rand.nextInt(sentenceEnd.length()));
        previousSentenceWords.add(word);
    }

    /* Метод генерирования абзацев */
    public void paragraphGenerator(int n1, int n2, double givenProb){
        for (int i=0; i<n2; i++){
            sentenceGenerator(n1);
            paragraph = paragraph + previousSentence;
            paragraph = paragraph + " ";
            if (probsGenerator(givenProb)) {
                String x = words.get(rand.nextInt(words.size()));
                //System.out.println("Слово: " + x + " из массива words");
                sentenceGenerator(n1);
                String newSentence = previousSentence.substring(0, previousSentence.length() - 2) + " " + x + sentenceEnd.charAt(rand.nextInt(sentenceEnd.length())) + " ";
                paragraph = paragraph + newSentence;
                //System.out.println("Попало в предложение: " + newSentence);
                //System.out.println("Вероятность попадания задана как " + givenProb + "\n");
                i++;
            }
        }
        paragraph = paragraph + "\r\n";
    }

    /* Метод реализации заданной вероятноти probability вхождения слова из массова words в предложение */
    public boolean probsGenerator(double givenProb){
        double random = rand.nextDouble();
        if (random < givenProb) {
            return true;
        } else { return false; }
    }

    /* Основной метод класса по генерированию по условиям:
    * - задается каталог для сохранения файлов;
    * - определяется количество генерируемых файлов;
    * - генерируются файлы заданного размера;
    * - задается вероятность попадания слов из массива words в каждое предложение;
    * - передается массив words */
    public void getFiles(String path, int n, int size, int probability, String[] words) {
        setWords(Arrays.asList(words));
        for (int i = 0; i < n; i++){
            byte[] buffer = new byte[0];
            int x = 0;
            FileOutputStream y = serialisation(path, i);
            while (x < size) {
                    paragraphGenerator(15, 20, (double) probability / 100);
                    buffer = paragraph.getBytes();
                    x = buffer.length;
                try {
                    y.write(buffer, 0, size);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                paragraph = "";
                paragraphGenerator(15, 20, (double) probability / 100);
                    //System.out.println(x);
            }
        }
    }

    /* Вспомогательный метод для сохранения файлов */
    public FileOutputStream serialisation(String path, int i) {
        try {
            multipleFos.add(new FileOutputStream(path + "fileNumber" + (i+1) + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return multipleFos.get(i);
    }

    /* Вспомогательный сеттер для назначения массива words в поле класса */
    public void setWords(List<String> words) {
        this.words = words;
    }
}
