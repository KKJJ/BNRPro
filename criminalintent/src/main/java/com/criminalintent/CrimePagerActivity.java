package com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.criminalintent.crime_id";

    private List<Crime> mCrimes;
    private ViewPager mViewPager;
    private UUID mCrimeId;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.getInstance(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);

                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return super.isViewFromObject(view, object);
            }

        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(mCrimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
//        mViewPager.setOffscreenPageLimit(3); // 表示当前页面两边各向前向后缓存3个

    }


}
