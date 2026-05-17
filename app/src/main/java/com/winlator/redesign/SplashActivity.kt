package com.winlator.redesign

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.winlator.redesign.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition { false }

        startSplashAnimation()
    }

    private fun startSplashAnimation() {
        val glowTop = binding.viewGlowTop
        val glowBottom = binding.viewGlowBottom
        val layoutLogo = binding.layoutLogo
        val layoutTagline = binding.layoutTagline
        val layoutLoading = binding.layoutLoading

        // Glow in
        val glowTopAnim = ObjectAnimator.ofFloat(glowTop, "alpha", 0f, 1f).apply {
            duration = 1000
            startDelay = 100
            interpolator = DecelerateInterpolator()
        }
        val glowBottomAnim = ObjectAnimator.ofFloat(glowBottom, "alpha", 0f, 1f).apply {
            duration = 1000
            startDelay = 200
            interpolator = DecelerateInterpolator()
        }

        // Logo entrance
        val logoAlphaAnim = ObjectAnimator.ofFloat(layoutLogo, "alpha", 0f, 1f).apply {
            duration = 700
            startDelay = 300
            interpolator = DecelerateInterpolator()
        }
        val logoScaleXAnim = ObjectAnimator.ofFloat(layoutLogo, "scaleX", 0.7f, 1f).apply {
            duration = 700
            startDelay = 300
            interpolator = OvershootInterpolator(1.2f)
        }
        val logoScaleYAnim = ObjectAnimator.ofFloat(layoutLogo, "scaleY", 0.7f, 1f).apply {
            duration = 700
            startDelay = 300
            interpolator = OvershootInterpolator(1.2f)
        }

        // Tagline fade in
        val taglineAnim = ObjectAnimator.ofFloat(layoutTagline, "alpha", 0f, 1f).apply {
            duration = 600
            startDelay = 800
            interpolator = DecelerateInterpolator()
        }

        // Loading fade in
        val loadingAnim = ObjectAnimator.ofFloat(layoutLoading, "alpha", 0f, 1f).apply {
            duration = 500
            startDelay = 1200
            interpolator = DecelerateInterpolator()
        }

        AnimatorSet().apply {
            playTogether(
                glowTopAnim, glowBottomAnim,
                logoAlphaAnim, logoScaleXAnim, logoScaleYAnim,
                taglineAnim, loadingAnim
            )
            start()
        }

        // Navigate to main after animation
        binding.root.postDelayed({
            navigateToMain()
        }, 2200)
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
