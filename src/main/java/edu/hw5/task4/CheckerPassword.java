package edu.hw5.task4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerPassword {
    public static boolean isPasswordCorrect(String password) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
