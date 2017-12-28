package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionView;
    TextView mScore;
    ProgressBar mProgressBar;
    int index = 0;
    int questionId;
    int score = 0;

    // TODO: Uncomment to create question bank
    private QuestionAnswer[] mQuestionBank = new QuestionAnswer[] {
            new QuestionAnswer(R.string.question_1, true),
            new QuestionAnswer(R.string.question_2, false),
            new QuestionAnswer(R.string.question_3, true),
            new QuestionAnswer(R.string.question_4, false),
            new QuestionAnswer(R.string.question_5, false),
            new QuestionAnswer(R.string.question_6, true),
            new QuestionAnswer(R.string.question_7, true),
            new QuestionAnswer(R.string.question_8, true),
            new QuestionAnswer(R.string.question_9, false),
            new QuestionAnswer(R.string.question_10, false),
            new QuestionAnswer(R.string.question_11, true),
            new QuestionAnswer(R.string.question_12, false),
    };

    private final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {

            score = savedInstanceState.getInt("score");
            index = savedInstanceState.getInt("questionIndex");
        }
        else {
            score = 0;
            index = 0;
        }

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Quizzler", "True button pressed");
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Quizzler", "False button pressed");
                checkAnswer(false);
                updateQuestion();
            }
        });

        questionId = mQuestionBank[index].getQuestionId();
        mQuestionView = (TextView) findViewById(R.id.question_text_view);
        mQuestionView.setText(questionId);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mScore = (TextView) findViewById(R.id.score);
        mScore.setText("Score: " + score + "/" + mQuestionBank.length);

    }

    public void updateQuestion() {

        index = (index + 1) % mQuestionBank.length;

        if(index == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.game_over);
            alert.setCancelable(false);
            alert.setMessage("You scored " + score + " points!");
            alert.setPositiveButton(R.string.close_application, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        questionId = mQuestionBank[index].getQuestionId();
        mQuestionView.setText(questionId);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScore.setText("Score: " + score + "/" + mQuestionBank.length);

    }

    public void checkAnswer(boolean userAnswer) {

        boolean correctAnswer = mQuestionBank[index].isAnswer();

        if(correctAnswer == userAnswer) {

            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            score++;
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("score", score);
        outState.putInt("questionIndex", index);
    }
}
