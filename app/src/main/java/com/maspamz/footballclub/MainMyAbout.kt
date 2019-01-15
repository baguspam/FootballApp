package com.maspamz.footballclub

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.support.constraint.ConstraintSet
import android.view.WindowManager
import android.support.constraint.ConstraintLayout
import android.support.transition.TransitionManager
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main_about.*


/**
 * Created by Maspamz on 19/09/2018.
 *
 */

class MainMyAbout : AppCompatActivity() {

    private var isOpen = false
    private lateinit var layout1: ConstraintSet
    private lateinit var layout2:ConstraintSet
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var imageViewPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_about)
        supportActionBar?.title = "About Me"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorLavender)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorLavenderDark)
        }


        // changing the status bar color to transparent

        layout1 = ConstraintSet()
        layout2 = ConstraintSet()
        imageViewPhoto = photo
        constraintLayout = findViewById(R.id.constraint_layout)
        layout2.clone(this, R.layout.activity_main_about_expander)
        layout1.clone(constraintLayout)

        imageViewPhoto.setOnClickListener(View.OnClickListener {
            if(!isOpen) {
                TransitionManager.beginDelayedTransition(constraintLayout)
                layout2.applyTo(constraintLayout)
                isOpen = !isOpen
            } else {

                TransitionManager.beginDelayedTransition(constraintLayout)
                layout1.applyTo(constraintLayout)
                isOpen = !isOpen

            }
        })


    }
}