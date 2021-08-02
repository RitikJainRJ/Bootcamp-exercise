package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Piglatin {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine().toLowerCase();
        String[] words = string.split(" ");


        for(String word: words) {
            String result = "";

            if (word.matches("([aeiou]|xr|yt).*")) {
                result += word;
                result += "ay";
            }
            else {
                if (word.contains("y")) {
                    int index= word.indexOf("y");
                    result += word.substring(index);
                    result += word.substring(0, index);
                    result += "ay";
                }
                else {
                    for (int c = 0; c < word.length(); c++) {
                        int q = 0;

                        if ("aeiou".contains("" + word.charAt(c))) {
                            if (word.contains("qu")) {
                                q = 1;
                            }

                            result += word.substring(c + q);
                            result += (word.substring(0, c + q));
                            result += "ay";
                            break;
                        }
                    }
                }
            }
            System.out.print(result + " ");
        }
    }
}
