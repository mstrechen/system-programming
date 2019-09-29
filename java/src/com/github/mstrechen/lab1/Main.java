package com.github.mstrechen.lab1;

import com.github.mstrechen.lab1.utils.StringPair;
import com.github.mstrechen.lab1.wordsDist.WordsDistDict;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    static private String readFilename(){
        System.out.println("Input file (input.txt as default):");
        Scanner in = new Scanner(System.in);
        String res = in.nextLine();
        res = res.strip();
        if(res.length() == 0)
            return "input.txt";
        return res;
    }

    static private Iterable<StringPair> readAndProcessFile(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8
                )
        );
        StringBuilder currentWord = new StringBuilder();
        WordsDistDict distDict = new WordsDistDict();
        int current;
        while ((current = in.read()) != -1){
            char currentChar = (char) current;
            if(Character.isLetter(currentChar)){
                currentWord.append(currentChar);
            }
            else if(currentWord.length() > 0){
                distDict.addWord(currentWord.toString());
                currentWord = new StringBuilder();
            }
        }
        if(currentWord.length() > 0)
            distDict.addWord(currentWord.toString());
        return distDict.getPairs();
    }

    public static void main(String[] args) {
        try {
            Iterable<StringPair> res = readAndProcessFile(readFilename());
            System.out.println("The following pairs have the largest edit distance:");
            for(StringPair sp : res){
                System.out.println(sp.getFirst() + " and " + sp.getSecond());
            }
        } catch (IOException e){
            System.out.println("Something went wrong while processing ");
        }
    }
}
