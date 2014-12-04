package com.ciandt.cursoandroid.worldwondersapp.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.ciandt.cursoandroid.worldwondersapp.fragment.PlaceDetailFragment;
import com.ciandt.cursoandroid.worldwondersapp.fragment.PlaceListFragment;
import com.ciandt.cursoandroid.worldwondersapp.manager.LoginManager;

public class MainActivity extends Activity implements PlaceListFragment.OnPlaceSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginManager loginManager = new LoginManager(this);

        if (!loginManager.isUserLogged()) {
            actionClickLogout();
        }

        Boolean isTablet = getResources().getBoolean(R.bool.is_tablet);

        if (isTablet) {
            PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_container_place_detail, placeDetailFragment);
            fragmentTransaction.commit();
        } else {
            findViewById(R.id.frame_layout_container_place_detail).setVisibility(View.GONE);
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

    @Override
    public void onPlaceSelected(Place place) {
        Boolean isTablet = getResources().getBoolean(R.bool.is_tablet);

        if (isTablet) {
            PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment().newInstance(place);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container_place_detail, placeDetailFragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        } else {
            Intent intent = new Intent(this, PlaceDetailActivity.class);
            intent.putExtra(PlaceDetailFragment.SELECTED_PLACE, place);
            startActivity(intent);
        }
    }
}
