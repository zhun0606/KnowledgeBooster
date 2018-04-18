package com.assignment.knowledgebooster.FirebaseClass;

public class Pictionary {
    private String picUrl;
    private String author;
    private int totalAnswers;
    private int correctAnswer;

    public Pictionary(){    }

    public Pictionary(String picUrl, String author, int totalAnswers, int correctAnswer) {
        this.picUrl = picUrl;
        this.author = author;
        this.totalAnswers = totalAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Pictionary{" +
                "picUrl='" + picUrl + '\'' +
                ", author='" + author + '\'' +
                ", totalAnswers=" + totalAnswers +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
