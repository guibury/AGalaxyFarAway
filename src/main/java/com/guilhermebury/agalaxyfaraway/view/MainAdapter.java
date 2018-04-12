package com.guilhermebury.agalaxyfaraway.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.guilhermebury.agalaxyfaraway.model.EnumFilmsPosition;
import com.guilhermebury.agalaxyfaraway.domain.Film;
import com.guilhermebury.agalaxyfaraway.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guilherme Bury on 20/04/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.FilmHolder>{
    private Context context;
    private List<Film> filmList;
    boolean isFavorite;

    public class FilmHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.year)
        TextView releaseDate;

        @BindView(R.id.cover)
        ImageView cover;

        @BindView(R.id.favorite)
        ImageView favorite;

        private int holderPosition;

        public FilmHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    view.getContext().startActivity(new Intent(view.getContext(), FilmActivity.class)
                            .putExtra(filmList.get(getLayoutPosition()).getTitle(), getAdapterPosition()+1));
                }
            });
        }
    }

    public MainAdapter(Context context, List<Film> filmList) {
        this.context = context;
        this.filmList = filmList;
    }

    @Override
    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new FilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FilmHolder holder, int position) {
        Film film = filmList.get(position);
        holder.holderPosition = position;
        holder.title.setText(film.getTitle());
        holder.releaseDate.setText(film.getReleaseDate());

        Glide.with(context).load(film.getCover()).into(holder.cover);


        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFavorite = changeFavoriteImage(holder);
            }
        });
    }

    private boolean changeFavoriteImage(FilmHolder holder) {
        if (isFavorite) {
            Glide.with(context).load(R.drawable.not_favorite).into(holder.favorite);
            return false;
        } else {
            Glide.with(context).load(R.drawable.favorite).into(holder.favorite);
            favoriteText(holder.holderPosition);
            return true;
        }
    }

    private void favoriteText(int holderPosition) {
        EnumFilmsPosition filmsPosition = EnumFilmsPosition.values()[holderPosition];

        switch (filmsPosition) {
            case THE_PHANTOM_MENACE:
            case ATTACK_OF_THE_CLONES:
            case REVENGE_OF_THE_SITH:
                makeToastText(R.string.favorite_prequel);
                break;
            case A_NEW_HOPE:
            case RETURN_OF_THE_JEDI:
                makeToastText(R.string.favorite_old_school);
                break;
            case THE_EMPIRE_STRIKES_BACK:
                makeToastText(R.string.favorite_strikes);
                break;
            default:
                makeToastText(R.string.favorite_awakens);
        }
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    private void makeToastText(int messageId) {
        Toast.makeText(context, messageId, Toast.LENGTH_LONG).show();
    }
}