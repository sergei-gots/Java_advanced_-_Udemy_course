/*
 * Copyright (c) 2022.
 * To the Lesson 31 "REGular EXpressions. Part I."
 * See: http://RegExLib.com and especially http://RegExLib.com//CheatSheet.aspx
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

public class L31_RexEx {
    public static void main(String[] args) {
        String s_1234 = "1234";
        //1) Literal match:
        System.out.println("Literal matches:");
        System.out.println("s_1234.matches(\"1234\") = " + s_1234.matches("1234"));
        System.out.println("s_1234.matches(\"1235\") = " + s_1234.matches("1235"));
        //2) RegEx match:
        //1 Digit - \\d
        // \\ - prefaces a special symbol to convert it into a literal symbol.
        System.out.println("Using special symbols '\\\\' - to introduce a special symbol, " +
                "\n 'd' - for digits," +
                "\n '+' - to describe 1 or more entrances," +
                "\n '*' - to describe 0 or more entrances:");

        String s_aLetter = "d";
        String pattern_OneDigit = "\\d";
        System.out.println("s_aLetter.matches(pattern_OneDigit) = " + s_aLetter.matches(pattern_OneDigit));

        String pattern_Digits_0_or_more = "\\d*";
        String pattern_Digits_1_or_more = "\\d+";

        String s_0d = "";
        String s_1d = "1";
        System.out.println("s_0d.matches(pattern_Digits_0_or_more) = " + s_0d.matches(pattern_Digits_0_or_more));
        System.out.println("s_0d.matches(pattern_Digits_1_or_more) = " + s_0d.matches(pattern_Digits_1_or_more));
        System.out.println("s_1d.matches(pattern_Digits_1_or_more) = " + s_1d.matches(pattern_Digits_1_or_more));
        System.out.println();

        System.out.println("Using a special symbol '-' - to indicate a MINUSE-sign" +
                "\n '?\' - to point that previously denoted sign is optional," +
                "\n '( ... | ... | ...)' - to describe a set of possible variants:");
        String s_minus1234 = "-1234";
        String s_a1234 = "a1234";

        System.out.println("s_minus1234.matches(\"-?\\\\d+\") = " + s_minus1234.matches("-?\\d+"));
        System.out.println("s_1234.matches(\"-?\\\\d+\") = " + s_1234.matches("-?\\d+"));
        System.out.println("s_a1234.matches(\"-?\\\\d+\") = " + s_a1234.matches("-?\\d+"));

        String s_plus1234 = "+1234";
        System.out.println("s_plus1234.matches(\"-?\\\\d+\") = " + s_plus1234.matches("-?\\d+"));

        //(x|y|z) - one of the possibilities
        System.out.println("s_plus1234.matches(\"(-|\\\\+)?\\\\d+\") = " + s_plus1234.matches("(-|\\+)?\\d+"));
        System.out.println();

        // \\w - Next symbol is a letter
        //[] - describes a set
        //[a-zA-Z] - all the symbols of the latin alphabet
        //[abc] - One of the symbols: either a, b or c.
        //[0-9] - one digit, equals \\d
        System.out.println("Using special symbols '[]' to describe a set:");

        System.out.println("s_a1234.matches(\"[a-zA-Z]\\\\d+\") = " + s_a1234.matches("[a-zA-Z]\\d+"));
        String s_1a1234 = "1a1234";
        String s_abc1234 = "abc1234";
        System.out.println("s_1a1234.matches(\"[a-zA-Z]+\\\\d+\") = " + s_1a1234.matches("[a-zA-Z]+\\d+"));
        System.out.println("s_abc1234.matches(\"[a-zA-Z]+\\\\d+\") = " + s_abc1234.matches("[a-zA-Z]+\\d+"));
        System.out.println();

        //Except st = we use ^-special symbol:
        System.out.println("Using a special excepting symbol '^\':");
        String s_Hello = "Hello";
        String s_Hellao = "Hellao";
        String s_Hello1 = "Hello1";
        System.out.println("s_Hello.matches(\"[^aA]+\") = " + s_Hello.matches("[^aA]+"));
        System.out.println("s_Hello1.matches(\"[^aA]+\") = " + s_Hello1.matches("[^aA]+"));
        System.out.println("s_Hellao.matches(\"[^aA]+\") = " + s_Hellao.matches("[^aA]+"));
        System.out.println();

        //Special symbol '.' is used to describe ANY symbol:
        System.out.println("Using a special symbol '.' describing ANY symbol at all:");

        String url = "http://www.udemy.com";
        String url1 = "http://www.ud$#$%emy.com";
        String url2 = "http://www.yandex.ru";

        System.out.println("url = " + url);
        System.out.println("url.matches(\"http://www\\\\..+\\\\.(com|org)\") = "
                + url.matches("http://www\\..+\\.(com|org)"));

        System.out.println("url1 = " + url1);
        System.out.println("url1.matches(\"http://www\\\\..+\\\\.(com|org)\") = "
                + url1.matches("http://www\\..+\\.(com|org)"));

        System.out.println("url2 = " + url2);
        System.out.println("url2.matches(\"http://www\\\\..+\\\\.(com|org)\") = "
                + url2.matches("http://www\\..+\\.(com|org)"));
        System.out.println();

        //{DIGIT}   - assigns exact number of described symbols
        //{DIGIT, } - assigns minimal number of described symbols
        //{DIGIT1, DIGIT2} - assigns that number of described symbols can be in interval btw DIGIT1 and DIGIT2
        //e.g. "\\d{2} - here are expected 2 digits.
        System.out.println("Using { X [,[Y]] } to describe an amount of expected symbols: ");

        String sIndex = "199004";
        String sNotAnIndex = "1995555";
        System.out.println("sIndex = " + sIndex);
        System.out.println("sIndex.matches(\"\\\\d{6}\") = " + sIndex.matches("\\d{6}"));
        System.out.println("sNotAnIndex = " + sNotAnIndex);
        System.out.println("sNotAnIndex.matches(\"\\\\d{6}\") = " + sNotAnIndex.matches("\\d{6}"));

        //Using a special symbol 'w' to describe a symbol of the latin alphabet.
        // It equals [a-zA-Z]:

        System.out.println("Using a special symbol 'w' to describe a symbol of the latin alphabet: ");
        System.out.println("url1 = " + url1);
        System.out.println("url1.matches(\"http://www\\\\.\\\\w+\\\\.(com|org)\") = "
                + url1.matches("http://www\\.\\w+\\.(com|org)"));
        System.out.println("url = " + url);
        System.out.println("url.matches(\"http://www\\\\.\\\\w+\\\\.(com|org)\") = "
                + url.matches("http://www\\.\\w+\\.(com|org)"));

    }
}