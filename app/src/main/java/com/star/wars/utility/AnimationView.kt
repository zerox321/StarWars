package com.star.wars.utility

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import javax.inject.Inject

class AnimationView @Inject constructor() {
    fun animateView(view: View, animationId: Int, onAnimationEnd: () -> Unit) {
        val anim = AnimationUtils.loadAnimation(view.context, animationId)
        view.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) { onAnimationEnd.invoke() }
            override fun onAnimationStart(animation: Animation?) {}
        })
    }

}