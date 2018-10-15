package com.androidquiz.psergeyev.androidquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val mTrueButton by lazy {
        findViewById<Button>(R.id.true_button)
    }

    val mFalseButton by lazy {
        findViewById<Button>(R.id.false_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTrueButton.setOnClickListener {
            Toast.makeText(this, R.string.correct_toast_text, Toast.LENGTH_SHORT).show();
        }

        mFalseButton.setOnClickListener {
            Toast.makeText(this, R.string.incorrect_toast_text, Toast.LENGTH_SHORT).show();
        }
    }
}
