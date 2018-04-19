package com.assignment.knowledgebooster.FirebaseClass;

public class Scramble {
    private String questionId;
    private String question;
    private String author;
    private int totalAnswer;
    private int correctAnswer;

    public Scramble() {
    }

    public Scramble(String questionId, String question, String author, int totalAnswers, int correctAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.author = author;
        this.totalAnswer = totalAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalAnswer() {
        return totalAnswer;
    }

    public void setTotalAnswer(int totalAnswer) {
        this.totalAnswer = totalAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Scramble{" +
                "questionId='" + questionId + '\'' +
                ", question='" + question + '\'' +
                ", author='" + author + '\'' +
                ", totalAnswer=" + totalAnswer +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
