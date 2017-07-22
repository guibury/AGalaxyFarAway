package com.guilhermebury.agalaxyfaraway.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.guilhermebury.agalaxyfaraway.R;
import com.guilhermebury.agalaxyfaraway.adapter.BottomViewPagerAdapter;
import com.guilhermebury.agalaxyfaraway.domain.Film;
import com.guilhermebury.agalaxyfaraway.domain.Planet;
import com.guilhermebury.agalaxyfaraway.domain.Specie;
import com.guilhermebury.agalaxyfaraway.domain.Starship;
import com.guilhermebury.agalaxyfaraway.domain.Vehicle;
import com.guilhermebury.agalaxyfaraway.fragment.CharacterFragment;
import com.guilhermebury.agalaxyfaraway.fragment.PlanetFragment;
import com.guilhermebury.agalaxyfaraway.fragment.SpecieFragment;
import com.guilhermebury.agalaxyfaraway.fragment.StarshipFragment;
import com.guilhermebury.agalaxyfaraway.fragment.VehicleFragment;

import java.util.ArrayList;


/**
 * Created by Guilherme Bury on 20/04/2017.
 */

public class FilmActivity extends AppCompatActivity {


    //Domain Entities - Retrofit response
    Film film;
    Planet planet;
    Starship starship;
    Vehicle vehicle;
    Specie specie;

    //Fragments
    private Fragment currentFragment;
    private CharacterFragment characterFragment = new CharacterFragment();
    private PlanetFragment planetFragment = new PlanetFragment();
    private SpecieFragment specieFragment = new SpecieFragment();
    private StarshipFragment starshipFragment = new StarshipFragment();
    private VehicleFragment vehicleFragment = new VehicleFragment();

    //Bottom Navigation
    private BottomViewPagerAdapter bottomViewPagerAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private AHBottomNavigationViewPager viewPagerBottom;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        initUI();
    }

    private void initUI() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPagerBottom = (AHBottomNavigationViewPager) findViewById(R.id.view_pager_bottom);
        viewPagerBottom.setOffscreenPageLimit(4);

        bottomViewPagerAdapter = new BottomViewPagerAdapter(getSupportFragmentManager());
        bottomViewPagerAdapter.add(characterFragment);
        bottomViewPagerAdapter.add(planetFragment);
        bottomViewPagerAdapter.add(starshipFragment);
        bottomViewPagerAdapter.add(vehicleFragment);
        bottomViewPagerAdapter.add(specieFragment);

        viewPagerBottom.setAdapter(bottomViewPagerAdapter);
        currentFragment = bottomViewPagerAdapter.getCurrentFragment();

        final AHBottomNavigationItem characterItem = new AHBottomNavigationItem(R.string.title_characters, R.drawable.ic_character, R.color.colorTitle);
        final AHBottomNavigationItem planetItem = new AHBottomNavigationItem(R.string.title_planets, R.drawable.ic_planet, R.color.colorTitle);
        final AHBottomNavigationItem starshipItem = new AHBottomNavigationItem(R.string.title_starships, R.drawable.ic_starship, R.color.colorTitle);
        final AHBottomNavigationItem vehicleItem = new AHBottomNavigationItem(R.string.title_vehicles, R.drawable.ic_vehicle, R.color.colorTitle);
        final AHBottomNavigationItem specieItem = new AHBottomNavigationItem(R.string.title_species, R.drawable.ic_specie, R.color.colorTitle);

        bottomNavigationItems.add(characterItem);
        bottomNavigationItems.add(planetItem);
        bottomNavigationItems.add(starshipItem);
        bottomNavigationItems.add(vehicleItem);
        bottomNavigationItems.add(specieItem);

        bottomNavigation.addItems(bottomNavigationItems);

        bottomNavigation.setAccentColor(Color.parseColor(getResources().getString(0+R.color.colorPrimaryDark)));
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (currentFragment != null) {
                    if (currentFragment instanceof CharacterFragment) {
                        characterFragment.willBeHidden();
                    } else if (currentFragment instanceof PlanetFragment) {
                        planetFragment.willBeHidden();
                    } else if (currentFragment instanceof StarshipFragment) {
                        starshipFragment.willBeHidden();
                    } else if (currentFragment instanceof VehicleFragment) {
                        vehicleFragment.willBeHidden();
                    } else if (currentFragment instanceof SpecieFragment) {
                            specieFragment.willBeHidden();
                }
            }
            viewPagerBottom.setCurrentItem(position, false);
            currentFragment = bottomViewPagerAdapter.getCurrentFragment();

            if (currentFragment instanceof CharacterFragment) {
                characterFragment.willBeDisplayed();
            } else if (currentFragment instanceof PlanetFragment) {
                planetFragment.willBeDisplayed();
            } else if (currentFragment instanceof StarshipFragment) {
                starshipFragment.willBeDisplayed();
            } else if (currentFragment instanceof VehicleFragment) {
                vehicleFragment.willBeDisplayed();
            } else if (currentFragment instanceof SpecieFragment) {
                specieFragment.willBeDisplayed();
            }
            return true;
            }
        });
    }
}