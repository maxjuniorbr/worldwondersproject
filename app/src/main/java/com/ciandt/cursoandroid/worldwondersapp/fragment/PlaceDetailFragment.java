package com.ciandt.cursoandroid.worldwondersapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class PlaceDetailFragment extends Fragment {
    public static final String SELECTED_PLACE = "selected_place";
    private Activity activity;
    private OnFragmentInteractionListener onFragmentInteractionListener;

    public PlaceDetailFragment() {
    }

    public static PlaceDetailFragment newInstance(final Place place) {
        PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_PLACE, place);
        placeDetailFragment.setArguments(bundle);
        return placeDetailFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_detail, container, false);
        Place place = loadPlace();

        if (place != null) {
            final ImageView imageFragItem = (ImageView) view.findViewById(R.id.imageFragItem);
            TextView textFragItemName = (TextView) view.findViewById(R.id.textFragItemName);
            TextView textFragCountry = (TextView) view.findViewById(R.id.textFragItemCountry);
            TextView textFragDescription = (TextView) view.findViewById(R.id.textFragItemDescription);

            imageFragItem.setVisibility(View.INVISIBLE);

            UrlImageViewHelper.setUrlDrawable(imageFragItem, place.placeImageUrl, new UrlImageViewCallback() {
                @Override
                public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
                    imageFragItem.setVisibility(View.VISIBLE);
                }
            });

            textFragItemName.setText(place.placeName);
            textFragCountry.setText(place.placeCountry);
            textFragDescription.setText(place.placeDescription);
        }

        return view;
    }

    private Place loadPlace() {
        Place place = (Place) this.activity.getIntent().getSerializableExtra(SELECTED_PLACE);
        if (place == null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                place = (Place) bundle.getSerializable(SELECTED_PLACE);
            }
        }
        return place;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}
