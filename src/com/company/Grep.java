package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    public static HashMap<Integer, String> findLines(String fileName, String pattern, boolean iFlag) throws FileNotFoundException {
        HashMap<Integer, String> map = new HashMap<>();
        String filePath = "resources/" + fileName;
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        Pattern r = Pattern.compile(pattern);
        if(iFlag)
            r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        int lineNumber = 0;

        while(sc.hasNextLine()){
            lineNumber++;
            String lineText = sc.nextLine();
            Matcher m = r.matcher(lineText);
            if(m.find())
                map.put(lineNumber, lineText);
        }
        return map;
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean nFlag = false;
        boolean lFlag = false;
        boolean iFlag = false;
        boolean vFlag = false;
        boolean xFlag = false;
        int i = 0;
        String pattern;
        Map<Integer, String> map;

        for(; i < args.length; i++)
            if(args[i].charAt(0) == '-'){
                switch(args[i].charAt(1)){
                    case 'n': nFlag = true;
                        break;
                    case 'l': lFlag = true;
                        break;
                    case 'i': iFlag = true;
                        break;
                    case 'v': vFlag = true;
                        break;
                    case 'x': xFlag = true;
                        break;
                }
            }
            else
                break;
        pattern = args[i++];
        for(; i < args.length; i++){
            map = findLines(args[i], pattern, iFlag);

            if(lFlag){
                if(!map.isEmpty())
                    System.out.println(args[i]);
            }
            else if(vFlag){
                String filePath = "resources/" + args[i];
                File file = new File(filePath);
                Scanner sc = new Scanner(file);
                int lineNumber = 0;

                while(sc.hasNextLine()){
                    lineNumber++;
                    String lineText = sc.nextLine();

                    if(!map.containsKey(lineNumber))
                        System.out.println(lineText);
                }
            }
            else {
                for (var entry : map.entrySet()) {
                    if(!xFlag) {
                        if (nFlag)
                            System.out.print(entry.getKey() + ":");
                        System.out.println(entry.getValue());
                    }
                    else{
                        if(entry.getValue().equals(pattern) ||
                                (entry.getValue().equalsIgnoreCase(pattern) && iFlag)){
                            if (nFlag)
                                System.out.print(entry.getKey() + ":");
                            System.out.println(entry.getValue());
                        }
                    }
                }
            }
        }
    }
}
