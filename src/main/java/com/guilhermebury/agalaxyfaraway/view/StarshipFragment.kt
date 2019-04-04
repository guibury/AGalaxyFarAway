package com.guilhermebury.agalaxyfaraway.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout

import com.guilhermebury.agalaxyfaraway.R

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class StarshipFragment : Fragment() {

    private var fragmentContainer: FrameLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceStace: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_starship, container, false)

        fragmentContainer = view.findViewById(R.id.fragment_starship_container)
        return view
    }

    fun willBeDisplayed() {
        if (fragmentContainer != null) {
            val fadeIn = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in)
            fragmentContainer!!.startAnimation(fadeIn)
        }
    }

    fun willBeHidden() {
        if (fragmentContainer != null) {
            AnimationUtils.loadAnimation(activity, android.R.anim.fade_out)
        }
    }
}