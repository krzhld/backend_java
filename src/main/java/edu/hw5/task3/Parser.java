package edu.hw5.task3;

import java.time.LocalDate;

public abstract class Parser {
    public Parser next;

    public void setNext(Parser next) {
        this.next = next;
    }

    public abstract LocalDate parse(String date);
}
