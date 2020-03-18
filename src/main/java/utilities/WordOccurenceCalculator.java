package utilities;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import domain.Word;
import utilities.Pair;

import java.net.URL;
import java.util.*;

public class WordOccurenceCalculator {
    private WordOccurenceCalculator() {}

    // This function gives out top K words among a list of words. In case of a tiebreaker, words are sorted lexicographically
    public static List<Pair<Word, Integer>> getTopKWords(List<Word> words, int k) {
        List<Pair<Word, Integer>> topKWords = new LinkedList<>();
        Map<Word, Integer> map = new HashMap<>();
        for(Word word: words) {
            // This logic can also be changed in future if some type of weight in introduced in the word
            if(map.containsKey(word)) {
                map.put(word, map.get(word)+1);
            } else {
                map.put(word, 1);
            }
        }
        PriorityQueue<Map.Entry<Word, Integer>> pq = new PriorityQueue<>(
                (a,b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );
        for(Map.Entry<Word, Integer> entry: map.entrySet()) {
            pq.offer(entry);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            topKWords.add(0, new Pair<>(pq.peek().getKey(), pq.peek().getValue()));
            pq.poll();
        }
        return topKWords;
    }

    // This function iterates through URL list and print top K words for each url
    public static void printTopKWordsFromUrlList(List<URL> urls, int k) {
        for (URL url: urls) {
            List<Word> words = UrlUtility.getWordsFromUrl(url);
            System.out.println("URL: " + url);
            printTopKWords(words, k);
        }
    }

    // This function prints top K words among a list of words
    public static void printTopKWords(List<Word> words, int k) {
        List<Pair<Word, Integer>> topKWords = getTopKWords(words, k);
        for (Pair<Word, Integer> currWord: topKWords) {
            Word word = currWord.getFirst();
            int count = currWord.getSecond();
            System.out.println(word + " - " + count);
        }
    }

    // This function counts the  number of occurence of a word, among a list of words
    private static int getWordOccurenceCountFromListOfWords(List<Word> words, Word targetWord) {
        int count = 0;
        for (Word word: words) {
            if (word.equals(targetWord)) {
                count++;
            }
        }
        return count;
    }

    private static int getWordOccurenceCountInUrl(URL url, Word targetWord) {
        List<Word> words = UrlUtility.getWordsFromUrl(url);
        return getWordOccurenceCountFromListOfWords(words, targetWord);
    }

    // This function prints the occurence of words, across a list of urls
    public static void printCombinedWordOccurenceFromUrls(List<URL> urls, List<Word> words) {
        System.out.println("Count of words across urls: ");
        for (Word word: words) {
            int currCount = 0;
            for (URL url: urls) {
                currCount += getWordOccurenceCountInUrl(url, word);
            }
            System.out.println(word + " - " + currCount);
        }
    }
    //
    //    public List<Word> getTopKWordsFromFile() {
    //        // Fetch the words from a file
    //        return getTopKWords();
    //    }
}
