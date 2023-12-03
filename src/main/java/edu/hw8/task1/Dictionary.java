package edu.hw8.task1;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    public static final Map<String, String> DICTIONARY;

    static {
        DICTIONARY = new HashMap<>();
        DICTIONARY.put(
            "личности",
            "Не переходи на личности там, где их нет"
        );
        DICTIONARY.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        DICTIONARY.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        DICTIONARY.put(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления"
        );
    }

    private Dictionary() {
    }

    public static String getOffense(String s) {
        for (var entry : DICTIONARY.entrySet()) {
            if (s.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "...";
    }
}
