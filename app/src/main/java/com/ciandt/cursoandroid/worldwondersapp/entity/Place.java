package com.ciandt.cursoandroid.worldwondersapp.entity;

import android.database.Cursor;

import com.ciandt.cursoandroid.worldwondersapp.database.table.PlaceTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Place extends BaseEntity implements Serializable {
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

    public Place(final JSONObject jsonObject) throws JSONException {
        this.id = this.getInteger(jsonObject, PlaceTable.JSON_ID);
        this.placeName = this.getString(jsonObject, PlaceTable.JSON_PLACE_NAME);
        this.placeCountry = this.getString(jsonObject, PlaceTable.JSON_PLACE_COUNTRY);
        this.placeDescription = this.getString(jsonObject, PlaceTable.JSON_PLACE_DESCRIPTION);
        this.placeImageUrl = this.getString(jsonObject, PlaceTable.JSON_PLACE_IMAGE_URL);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(id).append(" ").append(placeName).append(" ").append(placeCountry).append(" ").append(placeDescription).append(" ").append(placeImageUrl);
        return stringBuffer.toString();
    }
}
