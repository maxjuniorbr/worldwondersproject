package com.ciandt.cursoandroid.worldwondersapp.entity;

import android.database.Cursor;

import com.ciandt.cursoandroid.worldwondersapp.database.table.PlaceTable;

import java.io.Serializable;

public class Place implements Serializable {
    public Integer id;
    public String placeName;
    public String placeCountry;
    public String placeDescription;
    public String placeImageUrl;

    public Place(final Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(PlaceTable.ID));
        this.placeName = cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_NAME));
        this.placeCountry = cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_COUNTRY));
        this.placeDescription = cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_DESCRIPTION));
        this.placeImageUrl = cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_IMAGE_URL));
    }

    @Override
    public String toString() {
        return String.format("%1$s = %2$s; %3$s = %4$s; %5$s = %6$s; %7$s = %8$s; %9$s = %10$s;",
                PlaceTable.ID, this.id.toString(),
                PlaceTable.PLACE_NAME, this.placeName,
                PlaceTable.PLACE_COUNTRY, this.placeCountry,
                PlaceTable.PLACE_DESCRIPTION, this.placeDescription,
                PlaceTable.PLACE_IMAGE_URL, this.placeImageUrl);
    }
}
