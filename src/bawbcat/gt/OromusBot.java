package bawbcat.gt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.jibble.pircbot.PircBot;

public class OromusBot extends PircBot {

    public String IRCServer = "WebIRC.GameSurge.net";
    public String homeChannel = "#welcome";
    public static Random random = new Random();

    public OromusBot() {
        setLogin("OromusBot");
        setAutoNickChange(true);
    }
    
    /**
     * Gets a list of all the words (things separated by spaces).
     * @param message The string to get the words from.
     * @return All the words (things separated by spaces).
     */
    public List<String> getWords(String message) {
        List<String> words = new ArrayList<String>();
        for (String s : message.split(" ")) {
            words.add(s);
        }
        return words;
    }

    /**
     * Gets the word at the current index.
     * @param message The string to get the word from.
     * @param word The word's index.
     * @return The word at the given index, or null if there is no word at the given index.
     */
    public String getWord(String message, int word) {
        int currentWord = 0;
        for (String s : getWords(message)) {
            if (currentWord == word) {
                return s;
            } else {
                currentWord++;
            }
        }
        return null;
    }
    
    /**
     * Gets the index of the given word.
     * @param message The string to get the index from.
     * @param word The word who's index you want to get.
     * @return The index of the given word, or -1 if the word is not in the string.
     */
    public int getIndex(String message, String word) {
        int currentWord = 0;
        for (String s : getWords(message)) {
            if (s.equals(word)) {
                return currentWord;
            } else {
                currentWord++;
            }
        }
        return -1;
    }
    
    /**
     * Gets the instance (exact case, including case) of a given string.
     * @param message The string to get the instance from.
     * @param thing The string who's instance you want to get.
     * @return The instance of the given string.
     */
    public String getInstanceOf(String message, String thing) {
        if (containsPhrase(message, thing)) {
            List<String> parts = getWords(thing);
            List<String> matches = new ArrayList<String>();
            List<String> words = getWords(message);
            for (String part : parts) {
                for (String s : words) {
                    if (s.equalsIgnoreCase(part)) {
                        matches.add(s);
                        break;
                    }
                }
            }       
            if (matches.size() == parts.size()) {
                String reply = "";
                for (String s : matches) {
                    reply = reply + s;
                    if (!s.equals(matches.get(matches.size() - 1))) {
                        reply = reply + " ";
                    }
                }
                if (reply.equalsIgnoreCase(thing)) {
                    return reply;
                }
            }
        }
        return null;
    }
    
    /**
     * Checks to see if a given word is contained in a string.
     * @param message The string that you are checking for the given word.
     * @param word The word you are checking for.
     * @return True or false, based off of if the word is in the string.
     */
    public boolean contains(String message, String word) {       
        for (String s : getWords(message)) {
            if (s.equalsIgnoreCase(word)) {
                return true;
            } else {
                if (s.replace("?", "").replace("!", "").replace(".", "").replace(",", "").equalsIgnoreCase(word)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Gets a list of strings that are contained within the given message.
     * @param message The message you are checking.
     * @param list What strings you are looking for within the message.
     * @return Which strings were found in the string, or null if there were none.
     */
    public List<String> contains(String message, Object[] list) {
        List<String> objects = new ArrayList<String>();
        for (Object o : list) {
            String s = o.toString();
            if (contains(message, s)) {
                objects.add(o.toString());
            }
        }
        if (!objects.isEmpty()) {
            return objects;
        }
        return null;
    }
    
    /**
     * Gets a list of strings that are contained within the given message.
     * @param message The message you are checking.
     * @param list What strings you are looking for within the message.
     * @return Which strings were found in the string, or null if there were none.
     */
    public List<String> contains(String message, List<Object> list) {
        Object[] os = new Object[list.size()];
        int index = 0;
        for (Object o : list) {
            os[index] = 0;
            index++;
        }
        return contains(message, os);
    }

    public boolean containsPhrase(String message, String phrase) {
        List<String> phraseWords = getWords(phrase);
        int matchesNeeded = phraseWords.size();
        int matches = 0;
        for (String s : phraseWords) {
            if (contains(message, s)) {
                matches++;
            }
        }
        if (matches == matchesNeeded) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean containsPhrase(String message, String phrase, boolean partialMatch) {       
        if (partialMatch) {
            Object o = contains(message, phrase.split(" "));
            if (o != null) {
                return true;
            }
        } else {
            return containsPhrase(message, phrase);
        } 
        return false;
    }
    
    public List<String> containsPhrase(String message, Object[] array) {
        List<String> objects = new ArrayList<String>();
        for (Object o : array) {
            List<String> phraseWords = getWords(o.toString());
            int matchesNeeded = phraseWords.size();
            int matches = 0;
            for (String s : phraseWords) {
                if (contains(message, s)) {
                    matches++;
                }
            }
            if (matches == matchesNeeded) {
                objects.add(o.toString());
            }
        }
        if (!objects.isEmpty()) {
            return objects;
        }
        return null;
    }

    public List<String> containsPhrase(String message, Object[] array, boolean partialMatch) {
        if (partialMatch) {
            HashMap<Object, Integer> matches = new HashMap<Object, Integer>();
            List<String> strings = new ArrayList<String>();
            for (Object o : array) {
                int partMatches = 0;
                List<String> phraseWords = getWords(o.toString());
                for (String s : phraseWords) {
                    if (contains(message, s)) {
                        strings.add(o.toString());
                        partMatches++;
                    }
                }
                matches.put(o, partMatches);
            }
            List<String> sortedStrings = new ArrayList<String>();
            if (!strings.isEmpty()) {
                while (!strings.isEmpty()) {
                    int highestMatches = 0;
                    String highestS = null;
                    for (String s : strings) {
                        int partMatches = matches.get(s);
                        if (partMatches > highestMatches) {
                            highestS = s;
                            highestMatches = partMatches;
                        }
                    }
                    sortedStrings.add(highestS);
                    strings.remove(highestS);
                }
                return sortedStrings;
            }
        } else {
            return containsPhrase(message, array);
        }
        return null;
    }
    
    public static int randomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
