package com.androidquiz.psergeyev.androidquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val mTrueButton by lazy {
        findViewById<Button>(R.id.true_button)
    }

    val mFalseButton by lazy {
        findViewById<Button>(R.id.false_button)
    }

    val mNextButton by lazy {
        findViewById<Button>(R.id.next_button)
    }

    val mQuestionTextView by lazy {
        findViewById<TextView>(R.id.question_view)
    }

    var mCurrentQuestionIndex = 0;

    val questionsBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_americas, false),
            Question(R.string.question_asia, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, true)
        );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTrueButton.setOnClickListener {
            checkAnswer(true);
        }

        mFalseButton.setOnClickListener {
            checkAnswer(false);
        }

        updateQuestion();

        mNextButton.setOnClickListener {
            mCurrentQuestionIndex = (mCurrentQuestionIndex+1) % questionsBank.size;
            updateQuestion();
        }
    }

    fun updateQuestion() {
        val questionTextId = questionsBank[mCurrentQuestionIndex].textResId
        mQuestionTextView.setText(questionTextId);
    }

    fun checkAnswer(answer: Boolean) {
        if (answer == questionsBank[mCurrentQuestionIndex].isAnswerTrue)
            Toast.makeText(this, R.string.correct_toast_text, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.incorrect_toast_text, Toast.LENGTH_SHORT).show();
    }
}
