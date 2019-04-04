package com.guilhermebury.agalaxyfaraway.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.guilhermebury.agalaxyfaraway.R
import com.guilhermebury.agalaxyfaraway.contract.Contract
import com.guilhermebury.agalaxyfaraway.domain.Character
import com.guilhermebury.agalaxyfaraway.presenter.PresenterImpl
import kotlinx.android.synthetic.main.fragment_character.*
import java.lang.String.valueOf

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class CharacterFragment : Fragment(), Contract.View {

    private lateinit var presenter: Contract.Presenter
    private lateinit var progress: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceStace: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)

        presenter = PresenterImpl(this)

        apiCall()

        return view
    }

    private fun apiCall() {

        val filmPosition = activity?.intent?.extras?.get("FILM_POSITION")
        presenter.retrofitConverter(valueOf(filmPosition))
    }

    fun willBeDisplayed() {
        if (fragmentContainer != null) {
            val fadeIn = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in)
            fragmentContainer!!.startAnimation(fadeIn)
        }
    }

    fun willBeHidden() {
        if (fragmentContainer != null) {
            val fadeOut = AnimationUtils.loadAnimation(activity, android.R.anim.fade_out)
            fragmentContainer!!.startAnimation(fadeOut)
        }
    }


    override fun showLoading() {
        progress = ProgressDialog(activity)
        progress.setTitle("Searching...")
        progress.show()
    }

    override fun hideLoading() {
        progress.dismiss()
    }

    override fun showError() {
        Toast.makeText(context, "Fail", Toast.LENGTH_SHORT)
    }

    override fun onAPIInfoReceived(character: Character) {
        filmTitle.text = character.title
    }
}