package com.mwee.android.sqlite.base;

import android.database.sqlite.SQLiteDatabase;

public interface IDBCreate {
    void onCreate(SQLiteDatabase db);
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
