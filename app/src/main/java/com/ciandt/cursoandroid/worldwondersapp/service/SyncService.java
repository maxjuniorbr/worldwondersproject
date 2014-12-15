package com.ciandt.cursoandroid.worldwondersapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.NetworkUtil;
import com.ciandt.cursoandroid.worldwondersapp.service.syncer.PlaceSyncer;

public class SyncService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PlaceSyncer placeSyncer = new PlaceSyncer(this);
        placeSyncer.sync(intent);

        if (!NetworkUtil.isConnectionAvailable(this)) {
            Toast.makeText(this, getResources().getString(R.string.withoutconnection), Toast.LENGTH_SHORT).show();
        }

        return this.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
