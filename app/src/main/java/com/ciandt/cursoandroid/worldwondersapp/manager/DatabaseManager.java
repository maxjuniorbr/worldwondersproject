package com.ciandt.cursoandroid.worldwondersapp.manager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

import com.ciandt.cursoandroid.worldwondersapp.contentprovider.WorldWondersContentProvider;

public class DatabaseManager {
    private final Context context;
    private ContentResolver contentResolver;

    public DatabaseManager(final Context context) {
        this.context = context;
        this.contentResolver = context.getContentResolver();
    }

    public Long insert(final Uri uri, final ContentValues contValues) {
        Uri insert = contentResolver.insert(WorldWondersContentProvider.PLACE_CONTENT_URI, contValues);
        return ContentUris.parseId(insert);
    }

    public CursorLoader query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
        return new CursorLoader(context, uri, projection, selection, selectionArgs, sortOrder);
    }
}
