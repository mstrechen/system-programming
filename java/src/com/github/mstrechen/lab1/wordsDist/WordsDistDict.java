package com.github.mstrechen.lab1.wordsDist;


import java.util.HashSet;
import java.util.Set;
import com.github.mstrechen.lab1.utils.StringPair;

public class WordsDistDict {
    private Set<String> words;
    private int editDist = 0;
    private Set<StringPair> resPairs;

    public WordsDistDict(){
        this.words = new HashSet<>();
        this.resPairs = new HashSet<>();
    }

    private int getWordsEditDist(String s, String t){
        int dist = Math.abs(s.length() - t.length());
        for(int i = 0; i < s.length() && i < t.length(); i++)
            if(s.charAt(i) != t.charAt(i))
                dist++;
        return dist;
    }

    public void addWord(String word){
        word = word.toLowerCase();
        if(words.contains(word))
            return;
        words.add(word);
        for(String anotherWord : words) {
            int gotEditDist = this.getWordsEditDist(word, anotherWord);
            if(editDist < gotEditDist){
                editDist = gotEditDist;
                resPairs.clear();
            }
            if(editDist == gotEditDist) {
                resPairs.add(new StringPair(word, anotherWord));
            }
        }
    }

    public final Iterable<StringPair> getPairs(){
        return resPairs;
    }
}
