package com.ciandt.cursoandroid.worldwondersapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.entity.Place;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class PlaceCursorAdapter extends CursorAdapter {

    public PlaceCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View newView = inflater.inflate(R.layout.main_feed_item, viewGroup, false);
        return newView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Place place = new Place(cursor);

        final ImageView imageItem = (ImageView) view.findViewById(R.id.imageItem);
        TextView textItemName = (TextView) view.findViewById(R.id.textItemName);
        TextView textCountry = (TextView) view.findViewById(R.id.textItemCountry);
        TextView textDescription = (TextView) view.findViewById(R.id.textItemDescription);
        final ProgressBar progressBarImageItem = (ProgressBar) view.findViewById(R.id.progressBarImageItem);

        imageItem.setVisibility(View.INVISIBLE);
        progressBarImageItem.setVisibility(View.VISIBLE);

        UrlImageViewHelper.setUrlDrawable(imageItem, place.placeImageUrl, new UrlImageViewCallback() {
            @Override
            public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
                imageItem.setVisibility(View.VISIBLE);
                progressBarImageItem.setVisibility(View.INVISIBLE);
            }
        });

        textItemName.setText(place.placeName);
        textCountry.setText(place.placeCountry);
        textDescription.setText(place.placeDescription);
    }
}
