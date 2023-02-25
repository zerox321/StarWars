package com.star.wars.presentation.film

import android.os.Bundle
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.star.core.entities.remote.Film
import com.star.wars.R
import com.star.wars.databinding.FragmentFilmDetailBinding
import com.star.wars.utility.HtmlUtility
import com.star.wars.utility.TextToSpeechController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FilmDetailFragment : Fragment() {

    @Inject
    lateinit var htmlUtility: HtmlUtility

    @Inject
    lateinit var textToSpeechController: TextToSpeechController

    private val args by navArgs<FilmDetailFragmentArgs>()
    private val film get() = args.film

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!

    private val utteranceProgressListener by lazy {
        object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String) {
                binding.contentFilmDetails.playIcon.setImageResource(R.drawable.ic_stop_sound)
            }

            override fun onStop(utteranceId: String?, interrupted: Boolean) {
                super.onStop(utteranceId, interrupted)
                binding.contentFilmDetails.playIcon.setImageResource(R.drawable.ic_play_sound)
            }

            override fun onDone(utteranceId: String) {
                binding.contentFilmDetails.playIcon.setImageResource(R.drawable.ic_play_sound)
            }
            @Suppress("DEPRECATION")
            override fun onError(utteranceId: String) {
                binding.contentFilmDetails.playIcon.setImageResource(R.drawable.ic_play_sound)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindView(film = film)
    }

    private fun FragmentFilmDetailBinding.bindView(film: Film?) {
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        toolbar.title = film?.title
        topMovieDetails.titleTv.text = film?.title
        contentFilmDetails.playIcon.setOnClickListener {
            textToSpeechController.speak(film?.opening_crawl)
        }
        contentFilmDetails.desc.text = htmlUtility.fromHtml(film?.opening_crawl)
        topMovieDetails.datesTv.text =
            htmlUtility.fromHtml(film?.getFormattedDate(header = getString(R.string.date)))
        textToSpeechController.attachProgressListener(utteranceProgressListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textToSpeechController.removeProgressListener()
        _binding = null
    }


}