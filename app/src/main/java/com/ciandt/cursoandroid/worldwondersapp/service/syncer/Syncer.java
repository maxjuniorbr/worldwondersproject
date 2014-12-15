package com.ciandt.cursoandroid.worldwondersapp.service.syncer;

import android.content.Intent;

import java.net.URISyntaxException;

public abstract class Syncer {
    public abstract void sync(final Intent intent) throws URISyntaxException;
}