package com.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Kuang on 2016/12/13.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();

//        Log.d("--Crime--date1--", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS E").format(mDate) + "--");
//        Log.d("--Crime--date2--", new SimpleDateFormat("E, MM dd, yyyy").format(mDate) + "--");
//        Log.d("--Crime--date3--", new SimpleDateFormat("EEEE, MMMM dd, yyyy kk:mm").format(mDate) + "--");

//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        Log.d("--Crime--gregor--", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS E").format(gregorianCalendar.getTime()) + "--");


        // DateFormat
//        java.text.DateFormat dateFormat = DateFormat.getDateFormat();
//        String format = dateFormat.format(new Date());
//        Log.d("--format--date--", format);

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
