package com.guilhermebury.agalaxyfaraway.view;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guilhermebury.agalaxyfaraway.R;
import com.guilhermebury.agalaxyfaraway.domain.Film;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Guilherme Bury on 20/04/2017.
 */

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @InjectView(R.id.appbar)
    AppBarLayout appBarLayout;

    private MainAdapter adapter;
    private List<Film> films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        films = new ArrayList<>();
        adapter = new MainAdapter(this, films);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareFilms();

        try {
            Glide.with(this).load(R.drawable.main_cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar() {
        collapsingToolbar.setTitle(getString(R.string.toolbar));
        appBarLayout.setExpanded(true);

        //hiding and showing title on scroll
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                }
            }
        });
    }

    private void prepareFilms() {
        int[] covers = new int[]{
                R.drawable.new_hope,
                R.drawable.strikes_back,
                R.drawable.return_jedi,
                R.drawable.phanton_menace,
                R.drawable.attack_clones,
                R.drawable.revenge_sith,
                R.drawable.force_awakens
        };

        Film tempFilm = new Film(getString(R.string.title_film_one), getString(R.string.year_film_one), covers[0]);
        films.add(tempFilm);

        tempFilm = new Film(getString(R.string.title_film_two), getString(R.string.year_film_two), covers[1]);
        films.add(tempFilm);

        tempFilm = new Film(getString(R.string.title_film_three), getString(R.string.year_film_three), covers[2]);
        films.add(tempFilm);

        tempFilm = new Film(getString(R.string.title_film_four), getString(R.string.year_film_four), covers[3]);
        films.add(tempFilm);

        tempFilm = new Film(getString(R.string.title_film_five), getString(R.string.year_film_five), covers[4]);
        films.add(tempFilm);

        tempFilm = new Film(getString(R.string.title_film_six), getString(R.string.year_film_six), covers[5]);
        films.add(tempFilm);

        tempFilm = new Film(getString(R.string.title_film_seven), getString(R.string.year_film_seven), covers[6]);
        films.add(tempFilm);
    }

    //Equal margin around the grid item
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount; //item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }
}