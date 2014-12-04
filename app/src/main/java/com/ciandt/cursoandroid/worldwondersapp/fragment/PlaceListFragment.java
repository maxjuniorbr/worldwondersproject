package com.ciandt.cursoandroid.worldwondersapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.adapter.PlaceCursorAdapter;
import com.ciandt.cursoandroid.worldwondersapp.contentprovider.WorldWondersContentProvider;
import com.ciandt.cursoandroid.worldwondersapp.database.table.PlaceTable;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.ciandt.cursoandroid.worldwondersapp.manager.DatabaseManager;

public class PlaceListFragment extends Fragment {
    public static final String SELECTED_PLACE = "selected_place";
    private Activity activity;
    private OnPlaceSelectedListener onPlaceSelectedListener;

    public PlaceListFragment() {
        // Required empty public constructor
    }

    public static PlaceListFragment newInstance(final Place place) {
        PlaceListFragment placeListFragment = new PlaceListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_PLACE, place);
        placeListFragment.setArguments(bundle);
        return placeListFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        onPlaceSelectedListener = (OnPlaceSelectedListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);
        final PlaceCursorAdapter placeCursorAdapter = new PlaceCursorAdapter(activity, null);
        final DatabaseManager databaseManager = new DatabaseManager(activity);
        final ListView listView = (ListView) view.findViewById(R.id.listView);

        listView.setAdapter(placeCursorAdapter);

        getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                CursorLoader cursorLoader = databaseManager.query(WorldWondersContentProvider.PLACE_CONTENT_URI, PlaceTable.ALL_COLUMNS, null, null, null);
                return cursorLoader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                placeCursorAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                placeCursorAdapter.swapCursor(null);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) listView.getAdapter().getItem(i);
                Place place = new Place(cursor);
                onPlaceSelectedListener.onPlaceSelected(place);
            }
        });

        return view;
    }

    public interface OnPlaceSelectedListener {
        public void onPlaceSelected(Place place);
    }
}
