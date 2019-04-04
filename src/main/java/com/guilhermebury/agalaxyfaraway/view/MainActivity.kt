package com.guilhermebury.agalaxyfaraway.view

import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

import com.bumptech.glide.Glide
import com.guilhermebury.agalaxyfaraway.R
import com.guilhermebury.agalaxyfaraway.contract.Contract
import com.guilhermebury.agalaxyfaraway.domain.Film
import com.guilhermebury.agalaxyfaraway.presenter.PresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

import java.util.ArrayList

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class MainActivity : AppCompatActivity() {

    private var adapter: MainAdapter? = null
    private var films: MutableList<Film>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initCollapsingToolbar()

        adapter = MainAdapter(this, films!!)

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter

        prepareFilms()

        try {
            Glide.with(this).load(R.drawable.main_cover).into(findViewById(R.id.backdrop))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initCollapsingToolbar() {
        collapsingToolbar.title = getString(R.string.toolbar)
        appBarLayout.setExpanded(true)

        //hiding and showing title on scroll
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                }
            }
        })
    }

    private fun prepareFilms() {
        val covers = intArrayOf(R.drawable.new_hope)//                R.drawable.strikes_back,
        //                R.drawable.return_jedi,
        //                R.drawable.phantom_menace,
        //                R.drawable.attack_clones,
        //                R.drawable.revenge_sith,
        //                R.drawable.force_awakens

        val tempFilm = Film(getString(R.string.title_film_one), getString(R.string.year_film_one), covers[0])
        films!!.add(tempFilm)

        //        tempFilm = new Film(getString(R.string.title_film_two), getString(R.string.year_film_two), covers[1]);
        //        films.add(tempFilm);
        //
        //        tempFilm = new Film(getString(R.string.title_film_three), getString(R.string.year_film_three), covers[2]);
        //        films.add(tempFilm);
        //
        //        tempFilm = new Film(getString(R.string.title_film_four), getString(R.string.year_film_four), covers[3]);
        //        films.add(tempFilm);
        //
        //        tempFilm = new Film(getString(R.string.title_film_five), getString(R.string.year_film_five), covers[4]);
        //        films.add(tempFilm);
        //
        //        tempFilm = new Film(getString(R.string.title_film_six), getString(R.string.year_film_six), covers[5]);
        //        films.add(tempFilm);
        //
        //        tempFilm = new Film(getString(R.string.title_film_seven), getString(R.string.year_film_seven), covers[6]);
        //        films.add(tempFilm);
    }

    //Equal margin around the grid item
    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount //item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        val resources = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics))
    }
}