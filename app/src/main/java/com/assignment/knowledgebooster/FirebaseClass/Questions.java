package com.assignment.knowledgebooster.FirebaseClass;

import java.util.ArrayList;

public class Questions {
    ArrayList<Pictionary> pictionaries;
    ArrayList<Scramble> scrambles;
    ArrayList<Selection> selections;

    public Questions() {
    }

    public Questions(ArrayList<Pictionary> pictionaries, ArrayList<Scramble> scrambles, ArrayList<Selection> selections) {
        this.pictionaries = pictionaries;
        this.scrambles = scrambles;
        this.selections = selections;
    }

    public ArrayList<Pictionary> getPictionaries() {
        return pictionaries;
    }

    public void setPictionaries(ArrayList<Pictionary> pictionaries) {
        this.pictionaries = pictionaries;
    }

    public ArrayList<Scramble> getScrambles() {
        return scrambles;
    }

    public void setScrambles(ArrayList<Scramble> scrambles) {
        this.scrambles = scrambles;
    }

    public ArrayList<Selection> getSelections() {
        return selections;
    }

    public void setSelections(ArrayList<Selection> selections) {
        this.selections = selections;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "pictionaries=" + pictionaries +
                ", scrambles=" + scrambles +
                ", selections=" + selections +
                '}';
    }
}
