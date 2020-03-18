package controllers;

import domain.Word;
import utilities.WordOccurenceCalculator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// In real world, this controller will expose some API's
public class WordOccurenceController {
    private WordOccurenceController() {}

    // This can be GET request for getting data as well
    public static void printWordOccurenceAcrossUrls(String urlFilePath, String wordFilePath) {
        try {
            List<URL> urls = new ArrayList<>();
            List<Word> words = new ArrayList<>();
            File file = new File(urlFilePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                urls.add(new URL(sc.nextLine().trim()));
            }
            file = new File(wordFilePath);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                words.add(new Word(sc.nextLine().trim()));
            }
            WordOccurenceCalculator.printCombinedWordOccurenceFromUrls(urls, words);
        } catch (IOException exception) {
            // Give appropriate response like
//            response = {
//                    error: 'Incorrect file path'
//            };
//            code will be 400
            exception.printStackTrace();
        } catch (Exception exception) {
            // Give appropriate response like
//            response = {
//                    error: 'Internal error'
//            };
//           code will be 500
            exception.printStackTrace();
        }
    }

    // This can be GET request for getting data as well
    public static void printTopKWords(String filePath, int count) {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            List<URL> urls = new ArrayList<>();
            while (sc.hasNextLine()) {
                urls.add(new URL(sc.nextLine().trim()));
            }
            WordOccurenceCalculator.printTopKWordsFromUrlList(urls, count);
            // Give appropriate response like
//            response = {
//                    error: '',
//                        message: 'Printed successfully' // If only printing
//                        words:  List of words, if its a get request. Same for above api
//            };
//           code will be 200
        } catch (IOException exception) {
            // Give appropriate response like
//            response = {
//                    error: 'Incorrect file path'
//            };
//           code will be 400
            exception.printStackTrace();
        } catch (Exception exception) {
            // Give appropriate response like
//            response = {
//                    error: 'Internal error'
//            };
//           code will be 500
            exception.printStackTrace();
        }
    }
}
