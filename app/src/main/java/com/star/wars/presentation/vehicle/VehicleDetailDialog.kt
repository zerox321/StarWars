package com.star.wars.presentation.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.star.wars.R
import com.star.wars.databinding.DialogVehicleDetailBinding
import com.star.wars.utility.HtmlUtility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class VehicleDetailDialog : DialogFragment() {
    @Inject
    lateinit var htmlUtility: HtmlUtility
    private val args by navArgs<VehicleDetailDialogArgs>()
    private val vehicle get() = args.vehicle
    private var _binding: DialogVehicleDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogVehicleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            titleTv.text = vehicle?.name
            dateTv.text = htmlUtility.fromHtml(
                html = vehicle?.getFormattedModel(header = getString(R.string.model))
            )
            heightTv.text =
                htmlUtility.fromHtml(html = vehicle?.getFormattedModel(header = getString(R.string.model)))

//            val vehicle_class: String? = "",
//            val cargo_capacity: String? = "",
//            val consumables: String? = "",
//            val crew: String? = "",
//            val length: String? = "",
//            val manufacturer: String? = "",
//            val cost_in_credits: String? = "",
//            val passengers: String? = "",
//            val max_atmosphering_speed: String? = "",

            closeIv.setOnClickListener { this@VehicleDetailDialog.dismissAllowingStateLoss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}