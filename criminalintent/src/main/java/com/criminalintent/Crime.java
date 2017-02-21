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
    private String mSuspect;

    public Crime() {
        this(UUID.randomUUID());


//        Log.i("--Crime--date1--", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS E").format(mDate) + "--");
//        Log.i("--Crime--date2--", new SimpleDateFormat("E, MM dd, yyyy").format(mDate) + "--");
//        Log.i("--Crime--date3--", new SimpleDateFormat("EEEE, MMMM dd, yyyy kk:mm").format(mDate) + "--");

//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        Log.i("--Crime--gregor--", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS E").format(gregorianCalendar.getTime()) + "--");


        // DateFormat
//        java.text.DateFormat dateFormat = DateFormat.getDateFormat();
//        String format = dateFormat.format(new Date());
//        Log.i("--format--date--", format);

    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();

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

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        this.mSuspect = suspect;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crime crime = (Crime) o;

        if (mSolved != crime.mSolved) return false;
        if (mId != null ? !mId.equals(crime.mId) : crime.mId != null) return false;
        if (mTitle != null ? !mTitle.equals(crime.mTitle) : crime.mTitle != null) return false;
        return mDate != null ? mDate.equals(crime.mDate) : crime.mDate == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mDate != null ? mDate.hashCode() : 0);
        result = 31 * result + (mSolved ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mDate=" + mDate +
                ", mSolved=" + mSolved +
                ", mSuspect='" + mSuspect + '\'' +
                '}';
    }
}
