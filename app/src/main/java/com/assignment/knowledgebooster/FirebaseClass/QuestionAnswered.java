package com.assignment.knowledgebooster.FirebaseClass;

public class QuestionAnswered {
    private Questions questions;

    public QuestionAnswered() {
    }

    public QuestionAnswered(Questions questions) {
        this.questions = questions;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuestionAnswered{" +
                "questions=" + questions +
                '}';
    }
}
