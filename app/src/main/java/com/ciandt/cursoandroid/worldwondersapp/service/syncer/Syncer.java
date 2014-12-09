package com.ciandt.cursoandroid.worldwondersapp.service.syncer;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;

import com.ciandt.cursoandroid.worldwondersapp.database.table.PlaceTable;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;

import java.util.List;

public abstract class Syncer {

    public Syncer(final Service service) {
    }

    public abstract void sync(final Intent intent);

    private ContentValues[] prepareBulkInsertPlace(final List<Place> placeList) {
        ContentValues[] contValuesArray = new ContentValues[placeList.size()];
        int i = 0;

        for (Place place : placeList) {
            ContentValues contValues = new ContentValues();
            contValues.put(PlaceTable.ID, place.id);
            contValues.put(PlaceTable.PLACE_NAME, place.placeName);
            contValues.put(PlaceTable.PLACE_COUNTRY, place.placeCountry);
            contValues.put(PlaceTable.PLACE_DESCRIPTION, place.placeDescription);
            contValues.put(PlaceTable.PLACE_IMAGE_URL, place.placeImageUrl);
            contValuesArray[i++] = contValues;
        }

        return contValuesArray;
    }
}