package com.star.wars.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.star.wars.R
import com.star.wars.databinding.DialogVehicleDetailBinding
import com.star.wars.databinding.FragmentSplashBinding
import com.star.wars.utility.AnimationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    @Inject
    lateinit var animationView: AnimationView
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationView.animateView(
            view=binding.splashIcon,
            animationId=R.anim.splash,
            onAnimationEnd = { findNavController().navigate(R.id.openHomeFragment) }
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}