package com.guilhermebury.agalaxyfaraway.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import com.guilhermebury.agalaxyfaraway.util.EnumFilmsPosition
import com.guilhermebury.agalaxyfaraway.domain.Film
import com.guilhermebury.agalaxyfaraway.R


/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class MainAdapter(private val context: Context, private val filmList: List<Film>) : RecyclerView.Adapter<MainAdapter.FilmHolder>() {
    private var isFavorite = false

    inner class FilmHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var title: TextView

        internal var releaseDate: TextView

        internal var cover: ImageView

        internal var favorite: ImageView

        var holderPosition: Int = 0

        init {

            view.setOnClickListener {
                view.context.startActivity(Intent(view.context, FilmActivity::class.java)
                        .putExtra("FILM_POSITION", adapterPosition + 1))
            }

            title = view.findViewById(R.id.title)
            releaseDate = view.findViewById(R.id.year)
            cover = view.findViewById(R.id.cover)
            favorite = view.findViewById(R.id.favorite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)

        return FilmHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = filmList[position]
        holder.holderPosition = position
        holder.title.text = film.title
        holder.releaseDate.text = film.releaseDate

        Glide.with(context).load(film.cover).into(holder.cover)

        holder.favorite.setOnClickListener { isFavorite = changeFavoriteImage(holder) }
    }

    private fun changeFavoriteImage(holder: FilmHolder): Boolean {
        if (isFavorite) {
            Glide.with(context).load(R.drawable.not_favorite).into(holder.favorite)
            return false
        } else {
            Glide.with(context).load(R.drawable.favorite).into(holder.favorite)
            favoriteText(holder.holderPosition)
            return true
        }
    }

    private fun favoriteText(holderPosition: Int) {
        val filmsPosition = EnumFilmsPosition.values()[holderPosition]

        when (filmsPosition) {
            EnumFilmsPosition.THE_PHANTOM_MENACE, EnumFilmsPosition.ATTACK_OF_THE_CLONES, EnumFilmsPosition.REVENGE_OF_THE_SITH -> makeToastText(R.string.favorite_prequel)
            EnumFilmsPosition.A_NEW_HOPE, EnumFilmsPosition.RETURN_OF_THE_JEDI -> makeToastText(R.string.favorite_old_school)
            EnumFilmsPosition.THE_EMPIRE_STRIKES_BACK -> makeToastText(R.string.favorite_strikes)
            else -> makeToastText(R.string.favorite_awakens)
        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    private fun makeToastText(messageId: Int) {
        Toast.makeText(context, messageId, Toast.LENGTH_LONG).show()
    }
}