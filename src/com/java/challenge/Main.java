package com.java.challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static int stringReduction(String input) {
        Map<String, Character> combinationsToReplace = new HashMap<>();
        combinationsToReplace.put("ca", 'b');
        combinationsToReplace.put("ab", 'c');
        combinationsToReplace.put("ac", 'b');
        combinationsToReplace.put("bc", 'a');
        combinationsToReplace.put("ba", 'c');
        combinationsToReplace.put("cb", 'a');

        List<Character> list = input.chars().mapToObj(e-> (char) e).collect(Collectors.toList());
        String stringAux = list.stream().map(String::valueOf).collect(Collectors.joining());
        stringAux = sortString(stringAux);

        int i = 0;
        String pattern = "([a-zA-Z])\\1*";

        //checks using regex if the string contains one single char type
        while (!stringAux.matches(pattern)){
            if(list.get(i) != list.get(i+1)){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(list.get(i)).append(list.get(i+1));
                Character replaceChar = combinationsToReplace.get(stringBuilder.toString());
                char firstCharBkp = list.get(i);
                char secondCharBkp = list.get(i+1);
                list.remove(i);
                list.set(i,replaceChar);
                stringAux = list.stream().map(String::valueOf).collect(Collectors.joining());

                if(stringAux.matches(pattern) && list.size()>2){
                    list.set(i,firstCharBkp);
                    list.add(i+1,secondCharBkp);
                    stringAux = list.stream().map(String::valueOf).collect(Collectors.joining());
                    i++;
                } else {
                    i=0;
                }
            } else {
                i++;
            }
        }

       return stringAux.length();
    }

    public static String sortString(String str) {
        return str.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static void main(String[] args){
        //add your input here
        //String str = "ccccab";
        //String str = "abcc";
        //String str = "bcab";
        //String str = "cacbababaaaaacbbccaca";
        //String str = "ccccc";
        String str = "cab";

        System.out.println(stringReduction(str));
    }
}
