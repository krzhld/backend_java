package edu.project1;

sealed interface GuessMessage {
    String message();

    record Defeat() implements GuessMessage {
        @Override
        public String message() {
            return "You lose!\n";
        }
    }

    record Win() implements GuessMessage {
        @Override
        public String message() {
            return "You won!\n";
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    record SuccessfulGuess(Session curSession) implements GuessMessage {
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

    @SuppressWarnings("MultipleStringLiterals")
    record FailedGuess(Session curSession) implements GuessMessage {
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
