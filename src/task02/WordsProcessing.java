//package task02;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WordsProcessing {
//    String givenLinkString;
//    URL givenLink = new URL(givenLinkString);
//    BufferedReader in = new BufferedReader(new InputStreamReader(givenLink.openStream()));
//    List<String> words = new ArrayList<>();
//    String inputLine;
//
//    public WordsProcessing(String givenLinkStringTemp) throws IOException {
//        this.givenLinkString = givenLinkStringTemp;
//    }
//
//    public void generatingWordsArray() throws IOException {
//        while ((inputLine = in.readLine()) != null) {
//            words.add(inputLine);
//        }
//        in.close();
//    }
//
//    public List<String> getWords() {
//        return words;
//    }
//}
