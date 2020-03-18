import controllers.WordOccurenceController;

import java.net.MalformedURLException;

public class MachineCodingRujhanWordCounter {
    public static void main(String[] args) throws MalformedURLException {
        String wordFilePath = "/Users/rujhan/Desktop/wordList";
        String urlFilePath = "/Users/rujhan/Desktop/urlList";
        WordOccurenceController.printWordOccurenceAcrossUrls(urlFilePath, wordFilePath);
        WordOccurenceController.printTopKWords(urlFilePath, 3);
    }
}
