package com.ciandt.cursoandroid.worldwondersapp.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ciandt.cursoandroid.worldwondersapp.entity.User;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;
import com.ciandt.cursoandroid.worldwondersapp.integrator.GeneralIntegrator;

public class LoginManager {

    private final SharedPreferences sharedPreferences;
    private final GeneralIntegrator generalIntegrator;

    public LoginManager(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        generalIntegrator = new GeneralIntegrator(context);
    }

    public Boolean isUserLogged() {
        Boolean isUserLogged = Boolean.FALSE;
        String email = sharedPreferences.getString(Constants.Bundle.BUNDLE_USER_EMAIL, null);

        if (email != null) {
            isUserLogged = Boolean.TRUE;
        }

        return isUserLogged;
    }

    public User loginUser(final String userEmail, final String userPassword) {
        User user = generalIntegrator.loginUserOnBackend(userEmail, userPassword);

        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constants.Bundle.BUNDLE_USER_EMAIL, userEmail);
            editor.putString(Constants.Bundle.BUNDLE_USER_PASSWORD, userPassword);
            editor.commit();
        }

        return user;
    }

    public void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Constants.Bundle.BUNDLE_USER_EMAIL);
        editor.remove(Constants.Bundle.BUNDLE_USER_PASSWORD);
        editor.commit();
    }
}