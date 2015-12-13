package com.heroku.fig_know_wat.iamdriving.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heroku.fig_know_wat.iamdriving.R;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Alex on 13/12/15.
 */
@EFragment
public class JournalFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_journal, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
