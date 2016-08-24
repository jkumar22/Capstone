package com.awesome.jnpatel.capstone;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jnpatel on 7/15/16.
 */
public class home_Fragment extends ListFragment {
    static interface HomeListener {
        void itemClicked(long id);
    };

    private HomeListener listener;

    // creating the list view, that will display all the name of the cities.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
        // getting city names from Data class to display.
        String[] names = new String[Data.data.length];
        // adding the city names to the array adapter
        for (int i = 0; i < names.length; i++) {
            names[i] = Data.data[i].getName();
        }
        // inflating and setting the array adapters
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (HomeListener) activity;
    }

    // return item id that user has selected
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }

}
