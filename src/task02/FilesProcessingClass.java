package task02;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FilesProcessingClass {
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
            //word =  words.get(rand.nextInt(words.size()));
            previousSentence = previousSentence + word;
            previousSentence = previousSentence + commaSpace.get(rand.nextInt(commaSpace.size()));
            previousSentenceWords.add(word);
        }
        word = words.get(rand.nextInt(words.size()));
        previousSentence = previousSentence + words.get(rand.nextInt(words.size()));
        previousSentence = previousSentence + sentenceEnd.charAt(rand.nextInt(sentenceEnd.length()));
        previousSentenceWords.add(word);
        //previousSentenceWords.clear();
        //return previousSentence;
    }

    /* Метод генерирования абзацев */
    public void paragraphGenerator(int n1, int n2, double givenProb){
        for (int i=0; i<n2; i++){
            //paragraph = "";
            sentenceGenerator(n1);
            paragraph = paragraph + previousSentence;
            paragraph = paragraph + " ";
            if (probsGenerator(givenProb)) {

                //String x = previousSentenceWords.get(rand.nextInt(previousSentenceWords.size()));
                String x = words.get(rand.nextInt(words.size()));

                //System.out.println("Слово: " + x + " из массива words");
                //System.out.println("Из предыдущего предложения: "+ previousSentence);

                sentenceGenerator(n1);
                String newSentence = previousSentence.substring(0, previousSentence.length() - 2) + " " + x + sentenceEnd.charAt(rand.nextInt(sentenceEnd.length())) + " ";
                paragraph = paragraph + newSentence;
                //paragraph = paragraph + previousSentence.substring(0, previousSentence.length() - 2);
                //paragraph = paragraph + " " + x + sentenceEnd.charAt(rand.nextInt(sentenceEnd.length())) + " ";
                //System.out.println("Попало в предложение " + previousSentence);
                //System.out.println("Попало в предложение: " + newSentence);
                //System.out.println("Вероятность попадания задана как " + givenProb + "\n");
                i++;
            }
            //paragraph = paragraph + previousSentence;
            //paragraph = paragraph + " ";
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

    /* Определение размера файлов величиной size */
    //public void getFiles(String path,  int size, String[] words, int probability) {
    public void getFiles(String path, int n, int size, int probability, String[] words) {//, String[] words){//, ) {
        setWords(Arrays.asList(words));
        for (int i = 0; i < n; i++){
            byte[] buffer = new byte[0];
            int x = 0;
            //paragraphGenerator(15, 20, (double) probability / 100);
            FileOutputStream y = serialisation(path, i);
            while (x < size) {
                //try (serialisation(path, i)) {
                //try (FileOutputStream fos = new FileOutputStream(path + "fileNumber" + i+1 + ".txt")) {
                    //FileOutputStream y = serialisation(path, i);
                    paragraphGenerator(15, 20, (double) probability / 100);
                    buffer = paragraph.getBytes();
                    x = buffer.length;
                try {
                    y.write(buffer, 0, size);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //paragraphGenerator(15, 20, (double) probability / 100);
                paragraph = "";
                paragraphGenerator(15, 20, (double) probability / 100);
                //fos.write(buffer, 0, size);
                    //x = buffer.length;
                    System.out.println(x);
                    //paragraphGenerator(15, 20, (double) probability / 100);
                    //break;
                    //fos.close();
                //} catch (IOException ex) {
                  //  System.out.println(ex.getMessage());
                }
                //fos.close();
            }
        }
    //}

    public FileOutputStream serialisation(String path, int i) {

        try {
            multipleFos.add(new FileOutputStream(path + "fileNumber" + (i+1) + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //FileOutputStream fos = new FileOutputStream(path + "fileNumber" + i + ".txt"
        return multipleFos.get(i);
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
