package com.ciandt.cursoandroid.worldwondersapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.manager.LoginManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginManager loginManager = new LoginManager(this);

        if (!loginManager.isUserLogged()) {
            actionClickLogout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            actionClickLogout();
        } else if (id == R.id.action_rate) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String uriString = "market://details?id=com.ciandt.cursoandroid.worldwondersapp";
            intent.setData(Uri.parse(uriString));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionClickLogout() {
        LoginManager loginManager = new LoginManager(this);
        loginManager.logoutUser();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
