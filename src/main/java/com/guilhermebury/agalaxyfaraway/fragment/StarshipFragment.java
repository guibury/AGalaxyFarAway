package com.guilhermebury.agalaxyfaraway.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.guilhermebury.agalaxyfaraway.R;

/**
 * Created by Guilherme Bury on 30/06/2017.
 */

public class StarshipFragment extends Fragment {

    public StarshipFragment() {}

    private FrameLayout fragmentContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStace) {
        View view = inflater.inflate(R.layout.fragment_starship, container, false);

        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_starship_container);
        return view;
    }

    public void willBeDisplayed() {
        if(fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
        }
    }
}
