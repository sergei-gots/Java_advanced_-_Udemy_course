/*
 * Copyright (c) 2022.
 * To the Lesson 32 "REGular EXpressions. Part II."
 * See: http://RegExLib.com and especially http://RegExLib.com//CheatSheet.aspx
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.Arrays;

public class L32_RegEx {
    public static void main(String[] args) {
        //Method String.split() splits a string into an array of separated words
        //Delimiters are substrings satisfying a RegEx
        String s = "Hello there hey!   ";
        String[] s_words = s.split(" ");
        System.out.println("Arrays.toString(s_words) = " + Arrays.toString(s_words));
        
        String s1 = "hello.there.hey!";
        String[] s1_words = s.split("\\.");
        System.out.println("Arrays.toString(s1_words) = " + Arrays.toString(s1_words));

        String s2 = "1Hello123432there342hey!333";
        String[] s2_words = s.split("\\d+");
        System.out.println("Arrays.toString(s2_words) = " + Arrays.toString(s2_words));

        //Method String.replace() replaces substrings equals to a template string/char 
        // with a replacing string/char
        String s3 = "Hello. there. hey!. ";
        String s3Modified = s3.replace('.', ';');
        String s3Modified1 = s3.replace(". ", ":) ");
        System.out.println("s3Modified = " + s3Modified);
        System.out.println("s3Modified1 = " + s3Modified1);

        //Method String.replaceAll() replaces substrings satisfying  a template RegEx
        String s3_replaceAll = s3.replaceAll("\\. ", ":) ");
        System.out.println("s3_replaceAll = " + s3_replaceAll);

        //Method String.replaceFirst()
        String s3_replaceFirst = s3.replaceFirst("\\. ", ":) ");
        System.out.println("s3_replaceFirst = " + s3_replaceFirst);
    }
}
