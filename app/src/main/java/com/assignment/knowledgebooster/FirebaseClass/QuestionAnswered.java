package com.assignment.knowledgebooster.FirebaseClass;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswered {
    private List<String> pictionaries;
    private List<String> scrambles;
    private List<String> selections;

    public QuestionAnswered() { }

    public QuestionAnswered(List<String> pictionaries, List<String> scrambles, List<String> selections) {
        this.pictionaries = pictionaries;
        this.scrambles = scrambles;
        this.selections = selections;
    }

    public List<String> getPictionaries() {
        return pictionaries;
    }

    public void setPictionaries(List<String> pictionaries) {
        this.pictionaries = pictionaries;
    }

    public List<String> getScrambles() {
        return scrambles;
    }

    public void setScrambles(List<String> scrambles) {
        this.scrambles = scrambles;
    }

    public List<String> getSelections() {
        return selections;
    }

    public void setSelections(List<String> selections) {
        this.selections = selections;
    }

    @Override
    public String toString() {
        return "QuestionAnswered{" +
                "pictionaries=" + pictionaries +
                ", scrambles=" + scrambles +
                ", selections=" + selections +
                '}';
    }
}
