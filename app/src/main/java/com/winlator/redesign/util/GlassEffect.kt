package com.winlator.redesign.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator

object GlassEffect {

    fun fadeIn(view: View, duration: Long = 350, delay: Long = 0) {
        view.alpha = 0f
        view.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            this.duration = duration
            startDelay = delay
            interpolator = DecelerateInterpolator()
            start()
        }
    }

    fun scaleIn(view: View, duration: Long = 400, delay: Long = 0) {
        view.scaleX = 0.85f
        view.scaleY = 0.85f
        view.alpha = 0f
        view.visibility = View.VISIBLE
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
                    this.duration = duration
                    interpolator = DecelerateInterpolator()
                },
                ObjectAnimator.ofFloat(view, "scaleX", 0.85f, 1f).apply {
                    this.duration = duration
                    interpolator = OvershootInterpolator(1.1f)
                },
                ObjectAnimator.ofFloat(view, "scaleY", 0.85f, 1f).apply {
                    this.duration = duration
                    interpolator = OvershootInterpolator(1.1f)
                }
            )
            startDelay = delay
            start()
        }
    }

    fun slideUp(view: View, duration: Long = 400, delay: Long = 0) {
        view.translationY = 60f
        view.alpha = 0f
        view.visibility = View.VISIBLE
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 60f, 0f).apply {
                    this.duration = duration
                    interpolator = DecelerateInterpolator()
                },
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
                    this.duration = duration
                    interpolator = DecelerateInterpolator()
                }
            )
            startDelay = delay
            start()
        }
    }

    fun pulse(view: View) {
        AnimatorSet().apply {
            playSequentially(
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.05f).apply { duration = 150 },
                ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1f).apply { duration = 150 }
            )
            start()
        }
    }
}
