package com.assignment.knowledgebooster.FirebaseClass;

public class User {
    private String uid;
    private String name;
    private String email;
    private int totalQuestionAnswered;
    private int totalCorrectQuestionAnswered;

    public User() {
    }

    public User(String uid, String name, String email, int totalQuestionAnswered, int totalCorrectQuestionAnswered) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.totalQuestionAnswered = totalQuestionAnswered;
        this.totalCorrectQuestionAnswered = totalCorrectQuestionAnswered;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalQuestionAnswered() {
        return totalQuestionAnswered;
    }

    public void setTotalQuestionAnswered(int totalQuestionAnswered) {
        this.totalQuestionAnswered = totalQuestionAnswered;
    }

    public int getTotalCorrectQuestionAnswered() {
        return totalCorrectQuestionAnswered;
    }

    public void setTotalCorrectQuestionAnswered(int totalCorrectQuestionAnswered) {
        this.totalCorrectQuestionAnswered = totalCorrectQuestionAnswered;
    }
}
