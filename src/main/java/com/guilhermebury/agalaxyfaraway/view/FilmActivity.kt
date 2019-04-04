package com.guilhermebury.agalaxyfaraway.view

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.guilhermebury.agalaxyfaraway.R
import com.guilhermebury.agalaxyfaraway.domain.Film
import com.guilhermebury.agalaxyfaraway.domain.Planet
import com.guilhermebury.agalaxyfaraway.domain.Specie
import com.guilhermebury.agalaxyfaraway.domain.Starship
import com.guilhermebury.agalaxyfaraway.domain.Vehicle

import kotlinx.android.synthetic.main.activity_film.*

import java.util.ArrayList

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class FilmActivity : AppCompatActivity() {

    //Domain Entities - Retrofit response
    internal var film: Film? = null
    internal var planet: Planet? = null
    internal var starship: Starship? = null
    internal var vehicle: Vehicle? = null
    internal var specie: Specie? = null

    //Fragments
    private var currentFragment: Fragment? = null
    private val characterFragment = CharacterFragment()
    private val planetFragment = PlanetFragment()
    private val specieFragment = SpecieFragment()
    private val starshipFragment = StarshipFragment()
    private val vehicleFragment = VehicleFragment()

    //Bottom Navigation
    private var bottomViewPagerAdapter: BottomViewPagerAdapter? = null
    private val bottomNavigationItems = ArrayList<AHBottomNavigationItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        initUI()
    }

    private fun initUI() {
        viewPagerBottom.offscreenPageLimit = 4

        bottomViewPagerAdapter = BottomViewPagerAdapter(supportFragmentManager)
        bottomViewPagerAdapter!!.add(characterFragment)
        bottomViewPagerAdapter!!.add(planetFragment)
        bottomViewPagerAdapter!!.add(starshipFragment)
        bottomViewPagerAdapter!!.add(vehicleFragment)
        bottomViewPagerAdapter!!.add(specieFragment)

        viewPagerBottom.adapter = bottomViewPagerAdapter
        currentFragment = bottomViewPagerAdapter!!.currentFragment

        val characterItem = AHBottomNavigationItem(R.string.title_characters, R.drawable.ic_character, R.color.colorTitle)
        val planetItem = AHBottomNavigationItem(R.string.title_planets, R.drawable.ic_planet, R.color.colorTitle)
        val starshipItem = AHBottomNavigationItem(R.string.title_starships, R.drawable.ic_starship, R.color.colorTitle)
        val vehicleItem = AHBottomNavigationItem(R.string.title_vehicles, R.drawable.ic_vehicle, R.color.colorTitle)
        val specieItem = AHBottomNavigationItem(R.string.title_species, R.drawable.ic_specie, R.color.colorTitle)

        bottomNavigationItems.add(characterItem)
        bottomNavigationItems.add(planetItem)
        bottomNavigationItems.add(starshipItem)
        bottomNavigationItems.add(vehicleItem)
        bottomNavigationItems.add(specieItem)

        bottomNavigation.addItems(bottomNavigationItems)
        bottomNavigation.accentColor = Color.parseColor(resources.getString(0 + R.color.colorPrimaryDark))
        bottomNavigation.currentItem = 0
        bottomNavigation.setOnTabSelectedListener { position, _ ->

            if (currentFragment != null) {
                when (currentFragment) {
                    is CharacterFragment -> characterFragment.willBeHidden()
                    is PlanetFragment -> planetFragment.willBeHidden()
                    is StarshipFragment -> starshipFragment.willBeHidden()
                    is VehicleFragment -> vehicleFragment.willBeHidden()
                    is SpecieFragment -> specieFragment.willBeHidden()
                }
            }
            viewPagerBottom.setCurrentItem(position, false)
            currentFragment = bottomViewPagerAdapter!!.currentFragment

            when (currentFragment) {
                is CharacterFragment -> characterFragment.willBeDisplayed()
                is PlanetFragment -> planetFragment.willBeDisplayed()
                is StarshipFragment -> starshipFragment.willBeDisplayed()
                is VehicleFragment -> vehicleFragment.willBeDisplayed()
                is SpecieFragment -> specieFragment.willBeDisplayed()
            }
            true
        }
    }
}