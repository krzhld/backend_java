package edu.hw5.task8;

import java.util.regex.Pattern;

public class RegexesFor01AlphabetExtra {
    public static final Pattern ODD_LENGTH = Pattern.compile("^[01]([01]{2})*$");

    public static final Pattern STARTS_ZERO_ODD_LENGTH_OR_STARTS_ONE_EVEN_LENGTH =
        Pattern.compile("^(0([01]{2})*)|(1[01]([01]{2})*)$");

    public static final Pattern NUMBERS_ZERO_DIVISIBLE_BY_THREE = Pattern.compile("^((1*01*01*01*)*)$");

    public static final Pattern EVERY_ODD_SYMBOL_IS_ONE = Pattern.compile("^((1[01]1?)*)$");

}
