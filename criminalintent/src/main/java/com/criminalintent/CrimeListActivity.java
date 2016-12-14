package com.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Kuang on 2016/12/13.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
