package com.bnrpro;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bnrpro.answer_is_true"; //
    private static final String EXTRA_ANSWER_SHOWN = "com.bnrpro.answer_shown"; //


    private TextView mAnswerTextView;
    private TextView mCurrVersionTextView;
    private Button mShowAnswerButton;

    private boolean mAnswerIsTrue; // 正确答案

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mCurrVersionTextView = (TextView) findViewById(R.id.currVersionTextView);
        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);

        mCurrVersionTextView.setText("API level " + Build.VERSION.SDK_INT + "");

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });

    }

    /**
     * 解析返回结果中的值
     *
     * @return
     */
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    /**
     * @param isAnswerShown
     */
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static final Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
//        startActivity(intent);
        return intent;
    }


}
