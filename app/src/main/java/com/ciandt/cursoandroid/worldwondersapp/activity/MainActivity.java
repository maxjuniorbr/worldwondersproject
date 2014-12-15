package com.ciandt.cursoandroid.worldwondersapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.ciandt.cursoandroid.worldwondersapp.fragment.PlaceDetailFragment;
import com.ciandt.cursoandroid.worldwondersapp.fragment.PlaceListFragment;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;
import com.ciandt.cursoandroid.worldwondersapp.manager.LoginManager;
import com.ciandt.cursoandroid.worldwondersapp.service.SyncService;

public class MainActivity extends Activity implements PlaceListFragment.OnPlaceSelectedListener {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

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

        final Intent intent = new Intent(this, SyncService.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Service.Tag.RESULT_RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (Constants.Service.Status.FINISHED == resultCode) {
                    /*
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
                    notificationBuilder.setAutoCancel(true);
                    notificationBuilder.setTicker(getResources().getString(R.string.notification));
                    notificationBuilder.setSmallIcon(R.drawable.ic_launcher_app);
                    notificationBuilder.setContentTitle(getResources().getString(R.string.notification));
                    notificationBuilder.setContentText(getResources().getString(R.string.notification));

                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                            0,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    notificationBuilder.setContentIntent(pendingIntent);

                    int notificationId = 001;

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    */


                } else if (Constants.Service.Status.ERROR == resultCode) {

                }
            }
        });
        intent.putExtras(bundle);

        if (savedInstanceState == null) {
            // Dialog para confirmar o sync
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.confirmation_message);
            builder.setPositiveButton(R.string.confirmation_button,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startService(intent);
                        }
                    });
            builder.setNegativeButton(R.string.cancel_button,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
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
        } else if (id == R.id.action_about) {
            // Dialog para confirmar o sync
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.about_message);

            builder.setNeutralButton(R.string.logout,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
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

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
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
}
