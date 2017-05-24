package com.jason.tndgdemo.VlayoutUtils;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.tndgdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Blank1Fragment extends Fragment {


    public Blank1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank1, container, false);
    }

}
