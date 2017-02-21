package com.bnrpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "--MainActivity";
    private static final String KEY_INDEX = "index"; // 横竖屏切换时 保存数据的key
    private static final int REQUEST_CODE_CHEAT = 0; //

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mPreButton;
    private ImageButton mNextButton;
    private Button mCheatButton;
    private TextView mTextView;

    private Question[] mQuestionBank = {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPreButton = (ImageButton) findViewById(R.id.pre_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mTextView = (TextView) findViewById(R.id.question_text_view);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_LONG).show();
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_LONG).show();
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
                Log.i("mCurrentIndex--", "" + mCurrentIndex);
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNextQuestion();
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
//                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(getApplicationContext(), answerTrue);
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
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


        // ---------------------------------------------
        // NullPointerException 测试
//        ArrayList list = null;
//
//        try {
//            list.add("aaa");
//        } catch (Exception e) {
////            e.printStackTrace();
//            Log.e(TAG, "list is null", e);
//        }
        // ---------------------------------------------


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(this, "result : " + resultCode, Toast.LENGTH_LONG).show();
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    /**
     * 跳转到下一题
     */
    private void toNextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        mIsCheater = false;
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

        if (mIsCheater) {
            messageId = R.string.judgment_toast;
        } else {
            if (b == answerTrue) {
                messageId = R.string.correct_toast;
            } else {
                messageId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    /**
     * 更新显示的问题
     */
    private void updateQuestion() {
        // 通过打印异常  跟踪栈信息
//        Log.i(TAG, "updateQuestion--" + mCurrentIndex + ". ", new Exception());

        if (mCurrentIndex >= 0 && mCurrentIndex < mQuestionBank.length) {
            int questionId = mQuestionBank[mCurrentIndex].getTextResId();
            mTextView.setText(questionId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() called");
    }
}
