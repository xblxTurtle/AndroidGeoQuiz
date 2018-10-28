package com.androidquiz.psergeyev.androidquiz

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val mCheatButton by lazy {
        findViewById<Button>(R.id.cheat_button)
    }

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
    var mIsCheater = false;

    companion object {
        val REQUEST_CODE_CHEAT = 0;
    }

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
            mIsCheater = false;
            updateQuestion();
        }

        mCheatButton.setOnClickListener {
            val intent = CheatActivity.newIntent(this, questionsBank[mCurrentQuestionIndex].isAnswerTrue);
            startActivityForResult(intent, REQUEST_CODE_CHEAT);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            mIsCheater = CheatActivity.wasAnswerShown(data)
        }
    }

    fun updateQuestion() {
        val questionTextId = questionsBank[mCurrentQuestionIndex].textResId
        mQuestionTextView.setText(questionTextId);
    }

    fun checkAnswer(answer: Boolean) {

        Toast.makeText(this,
            when {
                (mIsCheater) -> R.string.judgment_toast
                (answer == questionsBank[mCurrentQuestionIndex].isAnswerTrue) -> R.string.correct_toast_text
                else -> R.string.incorrect_toast_text
            },
            Toast.LENGTH_SHORT).show();
    }
}
