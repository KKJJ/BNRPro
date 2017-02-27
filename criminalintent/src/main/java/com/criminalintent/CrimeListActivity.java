package com.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Kuang on 2016/12/13.
 */

public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks, CrimeListFragment.CheckCallbacks {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            CrimeFragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    private static final String TAG = "--CrimeListActivity";

    @Override
    public void onCrimeUpdated(Crime crime) {
        LogUtil.e(TAG, "onCrimeUpdated: 单页的时候调用吗？");
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    /**
     * 大屏幕机器的时候 Crime详情响应列表中checked改变
     *
     * @param crime
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(Crime crime, boolean isChecked) {
        LogUtil.e(TAG, "onCheckedChanged: 是时候调用了");
        if (findViewById(R.id.detail_fragment_container) == null) {
            return;
        }
        CrimeFragment detailFragment = (CrimeFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);


        LogUtil.i(TAG, "onCheckedChanged: " + crime.getId());
        LogUtil.i(TAG, "onCheckedChanged: " + detailFragment.mCrime.getId());

        if (crime.getId().equals(detailFragment.mCrime.getId())) {
            detailFragment.onCheckedChange(isChecked);
        }
    }

}
