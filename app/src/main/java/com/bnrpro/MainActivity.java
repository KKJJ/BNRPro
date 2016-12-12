package com.bnrpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "--MainActivity";
    private static final String KEY_INDEX = "index"; // 横竖屏切换时 保存数据的key


    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mPreButton;
    private ImageButton mNextButton;
    private TextView mTextView;

    private Question[] mQuestionBank = {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPreButton = (ImageButton) findViewById(R.id.pre_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mTextView = (TextView) findViewById(R.id.question_text_view);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                } else {
                    mCurrentIndex = mQuestionBank.length - Math.abs(mCurrentIndex - 1) % mQuestionBank.length;
                }
                Log.d("mCurrentIndex--", "" + mCurrentIndex);
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextQuestion();
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNextQuestion();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        // 初始显示题目
        updateQuestion();

        ArrayList list = null;

        try {
            list.add("aaa");
        } catch (Exception e) {
//            e.printStackTrace();
            Log.e(TAG, "list is null", e);
        }

    }

    /**
     * 跳转到下一题
     */
    private void toNextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }

    /**
     * 检查答案对错
     *
     * @param b
     */
    private void checkAnswer(boolean b) {

        int messageId = 0;
        boolean answerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if (b == answerTrue) {
            messageId = R.string.correct_toast;
        } else {
            messageId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    /**
     * 更新显示的问题
     */
    private void updateQuestion() {
        if (mCurrentIndex >= 0 && mCurrentIndex < mQuestionBank.length) {
            int questionId = mQuestionBank[mCurrentIndex].getTextResId();
            mTextView.setText(questionId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }
}
