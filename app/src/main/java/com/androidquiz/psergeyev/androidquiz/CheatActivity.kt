package com.androidquiz.psergeyev.androidquiz

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class CheatActivity : AppCompatActivity() {

    var mAnswerIsTrue : Boolean = false;
    val mShowAnswerButton by lazy {
        findViewById<Button>(R.id.show_answer_button)
    }
    val mAnswerTextView by lazy {
        findViewById<TextView>(R.id.answer_text_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mShowAnswerButton.setOnClickListener {
            mAnswerTextView.setText(if (mAnswerIsTrue) R.string.trueButton_text else R.string.falseButton_text)
            setAnswerShowResult(true)
        }
    }

    fun setAnswerShowResult(isAnswerShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(RESULT_OK, data)
    }

    companion object {
        val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

        fun newIntent(packageContext: Context, answerIsTrue: Boolean ): Intent {
            val intent = Intent(packageContext, (CheatActivity::class).java);
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
            return intent;
        }

        fun wasAnswerShown(result: Intent): Boolean {
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }

        val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    }
}
