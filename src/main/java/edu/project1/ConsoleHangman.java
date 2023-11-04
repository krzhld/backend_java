package edu.project1;

public class ConsoleHangman {
    private Dictionary dictionary;
    private Session curSession;
    private int maxAttempts;
    private boolean isStarted;
    private StringBuilder output;
    private static final int MAX_LENGTH_WORD = 20;
    private static final int DEFAULT_MAX_ATTEMPTS = 5;
    private static final String WIN_MESSAGE = new GuessMessage.Win().message();
    private static final String DEFEAT_MESSAGE = new GuessMessage.Defeat().message();

    public ConsoleHangman(int maxAttempts, Dictionary dictionary, StringBuilder output) {
        this.maxAttempts = maxAttempts;
        this.dictionary = dictionary;
        this.output = output;
        isStarted = false;
    }

    public ConsoleHangman(Dictionary dictionary, StringBuilder output) {
        this(DEFAULT_MAX_ATTEMPTS, dictionary, output);
    }

    public ConsoleHangman() {
    }

    public void start() {
        if (isStarted) {
            output.append("Game already started!\n");
        } else {
            output.setLength(0);
            if (dictionary.isEmpty()) {
                output.append("Cannot start a game: dictionary is empty!\n");
                return;
            }
            if (!addSession()) {
                output.append("Cannot start a game: too long word!\n");
            }
            isStarted = true;
        }
    }

    public void end() {
        isStarted = false;
    }

    private boolean addSession() {
        String wordToGuess = dictionary.getRandomWord();
        if (wordToGuess.length() >= MAX_LENGTH_WORD) {
            return false;
        }
        curSession = new Session(wordToGuess, maxAttempts);
        curSession.initWordChars();
        return true;
    }

    @SuppressWarnings("ReturnCount")
    public void makeMove(String guess) {
        if (!isStarted) {
            output.append("Game is not started yet!\n");
            return;
        }
        output.append("Guess a letter:\n");
        if (guess.equals("exit")) {
            isStarted = false;
            output.append("Game is finished.\n");
            return;
        }
        if (isValid(guess)) {
            char letter = Character.toLowerCase(guess.charAt(0));
            if (checkLetter(letter)) {
                openLetter(letter);
                output.append(new GuessMessage.SuccessfulGuess(curSession).message());
            } else {
                curSession.incrementAttempts();
                output.append(new GuessMessage.FailedGuess(curSession).message());
            }
            isFinished();
        }
    }

    private void isFinished() {
        if (curSession.getCurrentAttempts() == curSession.getMaxAttempts()) {
            isStarted = false;
            output.append(DEFEAT_MESSAGE);
        } else {
            for (char chr : curSession.getWordChars()) {
                if (chr == '*') {
                    return;
                }
            }
            isStarted = false;
            output.append(WIN_MESSAGE);
        }
    }

    private boolean isValid(String data) {
        return data.length() == 1 && Character.isLetter(data.charAt(0));
    }

    private boolean checkLetter(char letter) {
        for (int i = 0; i < curSession.getWordToGuess().length(); i++) {
            if (curSession.getWordToGuess().charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }

    private void openLetter(char letter) {
        for (int i = 0; i < curSession.getWordChars().length; i++) {
            if (curSession.getWordToGuess().charAt(i) == letter) {
                curSession.setCharInWordChars(i, letter);
            }
        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public String getCurrentState() {
        return output.toString();
    }

    public Session getCurSession() {
        return curSession;
    }
}
