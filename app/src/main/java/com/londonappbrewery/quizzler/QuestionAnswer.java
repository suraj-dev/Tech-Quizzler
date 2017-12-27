package com.londonappbrewery.quizzler;

/**
 * Created by surajnagaraj on 12/27/17.
 */

public class QuestionAnswer {

    private int questionId;
    private boolean answer;

    public QuestionAnswer(int id, boolean answer) {
        this.questionId = id;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
