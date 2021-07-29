package com.company;

import java.util.Arrays;
import java.util.List;

public class PigLatin {
    public static void convertIt(List<String> vowels, List<String> consonantClusters, String word){
        String result = "";

        for (var vowel : vowels) {
            if(word.startsWith(vowel)){
                result += word;
                result += "ay";
                System.out.println(result);
                return;
            }
        }
        for(var cc: consonantClusters) {
            if(word.startsWith(cc)){
                result += word.substring(cc.length());
                result += cc;
                result += "ay";
                System.out.println(result);
                return;
            }
        }
        if (word.length() > 2 && word.substring(1, 3).equals("qu")) {
            result += word.substring(3);
            result += word.charAt(0);
            result += "quay";
            System.out.println(result);
            return;
        }
        result += word.substring(1);
        result += word.charAt(0);
        result += "ay";
        System.out.println(result);
    }

    public static void main(String[] args){
        List<String> vowels = List.of("a", "e", "i", "o", "u", "yt", "xr", "xy");
        List<String> consonantClusters = List.of("thr", "sch", "th", "ch", "rh", "qu");
        String phrase = "xray yttria chair square rhythm my";

        List<String> words = Arrays.asList(phrase.split(" "));

        for(var word: words)
            convertIt(vowels, consonantClusters, word);
    }
}
