package edu.project1;

sealed interface GuessResult {
    String message();

    record Defeat() implements GuessResult {
        @Override
        public String message() {
            return "You lose!\n";
        }
    }

    record Win() implements GuessResult {
        @Override
        public String message() {
            return "You won!\n";
        }
    }

    record SuccessfulGuess(Session curSession) implements GuessResult {
        @Override
        public String message() {
            StringBuilder message = new StringBuilder();
            message.append("Hit!\n\n")
                .append("The word: ")
                .append(curSession.printWord())
                .append("\n\n");
            return message.toString();
        }
    }

    record FailedGuess(Session curSession) implements GuessResult {
        @Override
        public String message() {
            StringBuilder message = new StringBuilder();
            message.append("Missed, mistake ")
                .append(curSession.getCurrentAttempts())
                .append(" out of ")
                .append(curSession.getMaxAttempts())
                .append(".\n\n")
                .append("The word: ")
                .append(curSession.printWord())
                .append("\n\n");
            return message.toString();
        }
    }
}
