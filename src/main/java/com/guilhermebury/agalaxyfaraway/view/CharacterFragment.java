package com.guilhermebury.agalaxyfaraway.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guilhermebury.agalaxyfaraway.R;
import com.guilhermebury.agalaxyfaraway.model.RetrofitService;
import com.guilhermebury.agalaxyfaraway.model.SWApiService;
import com.guilhermebury.agalaxyfaraway.domain.Character;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Guilherme Bury on 20/04/2017.
 */

public class CharacterFragment extends Fragment {

    public CharacterFragment() {}

    private FrameLayout fragmentContainer;
    TextView title;
    ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStace) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_character_container);
        title = (TextView) view.findViewById(R.id.film_title);

        apiCall();

        return view;
    }

    private void apiCall() {
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Searching...");
        progress.show();

        String titleString = "1";//title.getText().toString();

        retrofitConverter(titleString);
    }


    private void retrofitConverter(String titleString) {
        RetrofitService service  = SWApiService.createService(RetrofitService.class);
        Call<Character> call = service.filmCall(titleString);

        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if (response.isSuccessful()) {
                    Character characterResponse = response.body();

                    if (characterResponse != null) {
                        title.setText(characterResponse.getTitle());

                        progress.dismiss();
                    }
                }
             }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT);
            }
        });
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
