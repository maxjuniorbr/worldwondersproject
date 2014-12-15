package com.ciandt.cursoandroid.worldwondersapp.service.syncer;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.ciandt.cursoandroid.worldwondersapp.contentprovider.WorldWondersContentProvider;
import com.ciandt.cursoandroid.worldwondersapp.database.table.PlaceTable;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.NetworkUtil;
import com.ciandt.cursoandroid.worldwondersapp.listener.DatabaseOperatorCallback;
import com.ciandt.cursoandroid.worldwondersapp.listener.IntegratorOperatorCallback;
import com.ciandt.cursoandroid.worldwondersapp.manager.DatabaseManager;
import com.ciandt.cursoandroid.worldwondersapp.manager.PlaceManager;

import java.util.List;

public class PlaceSyncer extends Syncer {

    private Service mService;

    public PlaceSyncer(final Service service) {
        this.mService = service;
    }

    @Override
    public void sync(Intent intent) {
        final ResultReceiver resultReceiver = intent.getParcelableExtra(Constants.Service.Tag.RESULT_RECEIVER);
        PlaceManager placeManager = new PlaceManager();

        try {
            placeManager.getAllPlaces(new IntegratorOperatorCallback<List<Place>>() {
                @Override
                public void onOperationCompleteSuccess(List<Place> places) {
                    ContentValues[] contentValues = prepareBulkInsertPlace(places);
                    DatabaseManager databaseManager = new DatabaseManager(mService);
                    Boolean deleteAll = NetworkUtil.isConnectionAvailable(mService);

                    databaseManager.bulkInsert(WorldWondersContentProvider.PLACE_CONTENT_URI,
                            contentValues,
                            deleteAll,
                            null,
                            null,
                            new DatabaseOperatorCallback<Integer>() {
                                @Override
                                public void onOperationSuccess(Integer integer) {
                                    resultReceiver.send(Constants.Service.Status.FINISHED, null);
                                }
                            });
                }

                @Override
                public void onOperationCompleteError() {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.Service.Tag.ERROR_MSG, String.valueOf("Erro ao efetuar o bulk insert"));
                    resultReceiver.send(Constants.Service.Status.ERROR, bundle);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
