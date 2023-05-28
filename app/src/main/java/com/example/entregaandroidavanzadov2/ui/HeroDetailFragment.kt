package com.example.entregaandroidavanzadov2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.entregaandroidavanzadov2.databinding.FragmentDetailBinding
import com.example.entregaandroidavanzadov2.ui.viewModels.HerosViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: HerosViewModel by viewModels()
    private val args: HeroDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val heroID = args.heroId

        viewModel.getHero(heroID)
        viewModel.getLocationsByHero(heroID)

        viewModel.locationResult.observe(viewLifecycleOwner) { success ->
            if (!success) {
                //TODO LOCATIONS FALILED
            } else {
                //TODO LOCATIONS MAP
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}