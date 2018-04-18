package com.assignment.knowledgebooster.FirebaseClass;

public class Selection {
    private String question;
    private String answer;
    private String fake1;
    private String fake2;
    private String fake3;
    private String author;
    private int totalAnswer;
    private int correctAnswer;

    public Selection() {
    }

    public Selection(String question, String answer, String fake1, String fake2, String fake3, String author, int totalAnswer, int correctAnswer) {
        this.question = question;
        this.answer = answer;
        this.fake1 = fake1;
        this.fake2 = fake2;
        this.fake3 = fake3;
        this.author = author;
        this.totalAnswer = totalAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFake1() {
        return fake1;
    }

    public void setFake1(String fake1) {
        this.fake1 = fake1;
    }

    public String getFake2() {
        return fake2;
    }

    public void setFake2(String fake2) {
        this.fake2 = fake2;
    }

    public String getFake3() {
        return fake3;
    }

    public void setFake3(String fake3) {
        this.fake3 = fake3;
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
        return "Selection{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", fake1='" + fake1 + '\'' +
                ", fake2='" + fake2 + '\'' +
                ", fake3='" + fake3 + '\'' +
                ", author='" + author + '\'' +
                ", totalAnswer=" + totalAnswer +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
