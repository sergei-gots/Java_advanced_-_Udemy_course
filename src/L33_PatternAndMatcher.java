/*
 * Copyright (c) 2022.
 * To the Lesson 33 "Package java.util.regex. Classes Pattern and Matcher."
 * See: http://RegExLib.com and especially http://RegExLib.com//CheatSheet.aspx
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L33_PatternAndMatcher {
    public static void main(String[] args) {
        String text = """
                >>Hello guys! I send you my e-mail Sergei.Gots@yahoo.com so we can
                >>keep in touch.\s
                Thanks, Serge! That's cool. I am sending you
                my address: student1@education.org. Let's stay in touch:)""";
        System.out.println("text = \n" + text);
        System.out.println();

        //There is a FACTORY-method for a creating an instance of a class will be used
        Pattern eMail = Pattern.compile("(\\w(\\w|\\d|\\.|-|_)*)@((\\w((\\w|\\d)*))\\.((com|org)))");
        //Matcher is an entity that will work with our text
        Matcher matcher = eMail.matcher(text);
        while(matcher.find()){
            System.out.println("matcher.group() = " + matcher.group());
            //group is any group within round brackets () in a RegEx:
            for(int i = 0; i<matcher.groupCount(); i++) {
                System.out.println("matcher.group(" + i + ") = " + matcher.group(i));
            }
            System.out.println();
        }
    }
}
