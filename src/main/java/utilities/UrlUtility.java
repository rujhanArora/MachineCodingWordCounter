package utilities;

import domain.Word;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UrlUtility {
    private UrlUtility() {}

    public static String getContentFromUrlAsString(URL url) {
        String result = "";
        try {
            Scanner sc = new Scanner(url.openStream());
            //Instantiating the StringBuffer class to hold the result
            StringBuffer sb = new StringBuffer();
            while(sc.hasNext()) {
                sb.append(sc.next());
                sb.append(" ");
            }
            //Retrieving the String from the String Buffer object
            result = sb.toString();
            result = result.replaceAll("<[^>]*>", "").trim();
            return result;
        } catch (IOException exception) {
            // Add some log shipping strategy and then decide further strategy based on logs
            exception.printStackTrace();
        }
        return result;
    }

    // This utility gives a list of words from the url data
    public static List<Word> getWordsFromUrl(URL url) {
        List<Word> words = new ArrayList<>();
        String result = getContentFromUrlAsString(url);
        List<String> stringWords = Arrays.asList(result.split("[\\s]+"));
        for (String stringWord: stringWords) {
            words.add(new Word(stringWord));
        }
        return words;
    }
}
