package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word>{

    /**
     *  Custom constructor for a new WordAdapter object.
     *  The context is used to inflate the layout file and the list is the data we want to populate
     *  into the lists.
     *
     * @param context Current context used to inflate the layout file.
     * @param words A list of Word objects to display in a list/view.
     */
    public WordAdapter(Activity context, ArrayList<Word> words){

        super(context, 0, words);
    }

    /**
     * Provides a view for an AdpaterView (ListView, GridView, etc.)
     *
     *
     * @param position The index in the list of data that should be displayed in the list item view
     * @param convertView The recycled view to pupulate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_tem.xml layout with the ID miwok_tv
        TextView miwokTV = (TextView) listItemView.findViewById(R.id.miwok_tv);
        // Get the Miwok word from the current Word object and set this text
        // on the miwok TextView
        miwokTV.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_tem.xml layout with the ID default_tv
        TextView defaultTV = (TextView) listItemView.findViewById(R.id.default_tv);
        // Get the default word from the current Word object and set this text
        // on the default TextView
        defaultTV.setText(currentWord.getDefaultTranslation());

        // Return the whole list item layout (with 2 TextViews) so that it can
        // be displayed in the ListView
        return listItemView;
    }
}
