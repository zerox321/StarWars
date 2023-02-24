package com.star.wars.presentation.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.star.wars.R
import com.star.wars.databinding.DialogPeopleDetailBinding
import com.star.wars.utility.HtmlUtility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeopleDetailDialog : DialogFragment() {

    @Inject
    lateinit var htmlUtility: HtmlUtility
    private val args by navArgs<PeopleDetailDialogArgs>()
    private val people get() = args.people
    private var _binding: DialogPeopleDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogPeopleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            titleTv.text = people?.name
            dateTv.text = htmlUtility.fromHtml(
                html = people?.getFormattedYear(header = getString(R.string.year))
            )
            heightTv.text = htmlUtility.fromHtml(
                html = people?.getFormattedHeight(
                    header = getString(R.string.height), metric = getString(R.string.cm)
                )
            )
            closeIv.setOnClickListener { this@PeopleDetailDialog.dismissAllowingStateLoss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}