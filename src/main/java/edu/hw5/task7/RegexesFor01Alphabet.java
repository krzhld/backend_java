package edu.hw5.task7;

import java.util.regex.Pattern;

public class RegexesFor01Alphabet {
    public static final Pattern MINIMUM_THREE_SYMBOLS_AND_THIRD_IS_ZERO = Pattern.compile("^[01]{2}0[01]*$");

    public static final Pattern BEGIN_SYMBOL_AND_END_EQUALS = Pattern.compile("^((0[01]*0)|(1[01]*1)|0|1)$");
    public static final Pattern MINIMUM_LENGTH_IS_ONE_MAX_IS_THREE = Pattern.compile("^[01]{1,3}$");

}
