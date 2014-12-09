package com.ciandt.cursoandroid.worldwondersapp.businesscoordinator;

import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.ciandt.cursoandroid.worldwondersapp.integrator.PlaceIntegrator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceBusinessCoordinator {
    public List<Place> getAllPlaces() {
        PlaceIntegrator placeIntegrator = new PlaceIntegrator();
        List<Place> places = new ArrayList<Place>();
        try {
            JSONObject jsonObject = placeIntegrator.getAllPlaces();
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                Place place = new Place(jsonArray.getJSONObject(i));
                places.add(place);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return places;
        }
    }
}
