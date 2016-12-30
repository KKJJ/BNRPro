package com.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Kuang on 2016/12/30.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuid = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        long datetime = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        int solved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuid));
        crime.setTitle(title);
        crime.setDate(new Date(datetime));
        crime.setSolved(solved != 0);

        return crime;
    }

}
