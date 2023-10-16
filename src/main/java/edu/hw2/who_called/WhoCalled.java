package edu.hw2.who_called;

public class WhoCalled {

    private WhoCalled() {
    }

    public static CallingInfo callingInfo() {
        Throwable stackTraceSupplier = new Exception();
        StackTraceElement callerStackElement = stackTraceSupplier.getStackTrace()[1];
        return new CallingInfo(
            callerStackElement.getClassName(),
            callerStackElement.getMethodName()
        );
    }
}
