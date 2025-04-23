package org.example;

public class Scores {
    String rawScore,totalScore;
    public Scores(String rawScore, String totalScore) {
        this.rawScore = rawScore;
        this.totalScore = totalScore;
    }

    public String getRawScore() {
        return rawScore;
    }

    public void setRawScore(String rawScore) {
        this.rawScore = rawScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
}
