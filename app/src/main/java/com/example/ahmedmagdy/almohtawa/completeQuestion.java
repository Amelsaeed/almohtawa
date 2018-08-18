package com.example.ahmedmagdy.almohtawa;

/**
 * Created by AHMED MAGDY on 7/19/2018.
 */

public class completeQuestion {
    String questionId;
    String questionI;
    String questionAnswer1;
    String questionAnswer2;
    String questionAnswer3;
    String questionAnswer4;
    String questionCorrectAnswer;
    public completeQuestion(){

    }

    public completeQuestion(String questionId, String questionI, String questionAnswer1, String questionAnswer2, String questionAnswer3, String questionAnswer4, String questionCorrectAnswer) {
        this.questionId = questionId;
        this.questionI = questionI;
        this.questionAnswer1 = questionAnswer1;
        this.questionAnswer2 = questionAnswer2;
        this.questionAnswer3 = questionAnswer3;
        this.questionAnswer4 = questionAnswer4;
        this.questionCorrectAnswer = questionCorrectAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionI() {
        return questionI;
    }

    public String getQuestionAnswer1() {
        return questionAnswer1;
    }

    public String getQuestionAnswer2() {
        return questionAnswer2;
    }

    public String getQuestionAnswer3() {
        return questionAnswer3;
    }

    public String getQuestionAnswer4() {
        return questionAnswer4;
    }

    public String getQuestionCorrectAnswer() {
        return questionCorrectAnswer;
    }


}
