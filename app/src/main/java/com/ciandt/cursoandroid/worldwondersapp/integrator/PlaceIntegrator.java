package com.ciandt.cursoandroid.worldwondersapp.integrator;

import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;

import org.json.JSONObject;

public class PlaceIntegrator extends BaseIntegrator {
    public JSONObject getAllPlaces() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) doGetRequest(Constants.Integrator.WorldWondersApi.PROTOCOL,
                    Constants.Integrator.WorldWondersApi.HOST,
                    Constants.Integrator.WorldWondersApi.WORLD_WONDERS_LIST);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return jsonObject;
        }
    }
}
