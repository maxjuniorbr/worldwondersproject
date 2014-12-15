package com.ciandt.cursoandroid.worldwondersapp.manager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;
import android.os.AsyncTask;

import com.ciandt.cursoandroid.worldwondersapp.listener.DatabaseOperatorCallback;

public class DatabaseManager {
    private final Context mContext;

    public DatabaseManager(final Context context) {
        this.mContext = context;
    }

    public Long insert(final Uri uri, final ContentValues contValues) {
        ContentResolver contentResolver = mContext.getContentResolver();
        return ContentUris.parseId(contentResolver.insert(uri, contValues));
    }

    public CursorLoader query(final Uri uri,
                              final String[] projection,
                              final String selection,
                              final String[] selectionArgs,
                              final String sortOrder) {
        return new CursorLoader(mContext, uri, projection, selection, selectionArgs, sortOrder);
    }

    public void bulkInsert(final Uri uri,
                           final ContentValues[] contentValues,
                           final boolean deleteBeforeInsert,
                           final String deleteSelecion,
                           final String[] deleteSelectionArgs,
                           final DatabaseOperatorCallback<Integer> integerDatabaseOperatorCallback) {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                ContentResolver contentResolver = mContext.getContentResolver();

                if (deleteBeforeInsert) {
                    contentResolver.delete(uri, deleteSelecion, deleteSelectionArgs);
                }

                return contentResolver.bulkInsert(uri, contentValues);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                integerDatabaseOperatorCallback.onOperationSuccess(integer);
            }
        }.execute();
    }
}
