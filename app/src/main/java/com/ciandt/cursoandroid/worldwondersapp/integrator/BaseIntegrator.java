package com.ciandt.cursoandroid.worldwondersapp.integrator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class BaseIntegrator {
    public Object doGetRequest(String protocol, String host, String path) throws URISyntaxException {
        URI uri = URIUtils.createURI(protocol, host, 80, path, null, null);
        HttpGet httpGet = new HttpGet(uri);
        HttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonObject = new JSONObject();

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseString = EntityUtils.toString(httpEntity);
            jsonObject = new JSONObject(responseString);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return jsonObject;
        }
    }
}
