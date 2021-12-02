package com.quangminh.timeforlife.model;

public class ModelWeek {
    int week;
    int score;
    int complete;
    int failed;
    int total_cv;

    public ModelWeek(int week, int score, int complete, int failed, int total_cv) {
        this.week = week;
        this.score = score;
        this.complete = complete;
        this.failed = failed;
        this.total_cv = total_cv;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getTotal_cv() {
        return total_cv;
    }

    public void setTotal_cv(int total_cv) {
        this.total_cv = total_cv;
    }
}
