package com.ciandt.cursoandroid.worldwondersapp.manager;

import android.os.AsyncTask;

import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;
import com.ciandt.cursoandroid.worldwondersapp.integrator.PlaceIntegrator;
import com.ciandt.cursoandroid.worldwondersapp.listener.IntegratorOperatorCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PlaceManager {
    public void getAllPlaces(final IntegratorOperatorCallback<List<Place>> listIntegratorOperatorCallback) throws URISyntaxException {
        new AsyncTask<Void, Void, List<Place>>() {

            @Override
            protected List<Place> doInBackground(Void... params) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject = (JSONObject) new PlaceIntegrator().doGetRequest(Constants.Integrator.WorldWondersApi.PROTOCOL, Constants.Integrator.WorldWondersApi.HOST, Constants.Integrator.WorldWondersApi.WORLD_WONDERS_LIST);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                List<Place> places = new ArrayList<Place>();
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Place place = new Place(jsonArray.getJSONObject(i));
                        places.add(place);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return places;
            }

            @Override
            protected void onPostExecute(List<Place> places) {
                super.onPostExecute(places);
                if (places != null) {
                    listIntegratorOperatorCallback.onOperationCompleteSuccess(places);
                } else {
                    listIntegratorOperatorCallback.onOperationCompleteError();
                }
            }
        }.execute();
    }
}
