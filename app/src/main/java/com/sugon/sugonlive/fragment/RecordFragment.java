package com.sugon.sugonlive.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sugon.sugonlive.R;

/**
 * Created by pjc on 2017/8/2.
 */

public class RecordFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View recordFragment = inflater.inflate(R.layout.record_fragment_layout, container, false);
        return recordFragment;
    }
}
