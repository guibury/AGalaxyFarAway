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

/**
 * Created by Guilherme Bury on 20/04/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.FilmHolder>{
    private Context context;
    private List<Film> filmList;
    boolean isFavorite;

    public class FilmHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView releaseDate;
        public ImageView cover;
        public ImageView favorite;
        public int holderPosition;

        public FilmHolder(final View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            releaseDate = (TextView) view.findViewById(R.id.year);
            cover = (ImageView) view.findViewById(R.id.cover);
            favorite = (ImageView) view.findViewById(R.id.favorite);

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
                //long adapterId = getItemId(holder.getAdapterPosition());
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
            favoriteText(holder);
            return true;
        }
    }

    private void favoriteText(FilmHolder holder) {
        if(holder != null) {
            if(holder.holderPosition == EnumFilmsPosition.THE_PHANTOM_MENACE.getPosition()
                    || holder.holderPosition == EnumFilmsPosition.ATTACK_OF_THE_CLONES.getPosition()
                    || holder.holderPosition == EnumFilmsPosition.REVENGE_OF_THE_SITH.getPosition()) {
                Toast.makeText(context, R.string.favorite_prequel, Toast.LENGTH_LONG).show();

            } else if(holder.holderPosition == EnumFilmsPosition.A_NEW_HOPE.getPosition()
                    || holder.holderPosition == EnumFilmsPosition.RETURN_OF_THE_JEDI.getPosition()) {
                Toast.makeText(context, R.string.favorite_old_school, Toast.LENGTH_LONG).show();

            } else if(holder.holderPosition == EnumFilmsPosition.THE_EMPIRE_STRIKES_BACK.getPosition()) {
                Toast.makeText(context, R.string.favorite_strikes, Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(context, R.string.favorite_awakens, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }
}