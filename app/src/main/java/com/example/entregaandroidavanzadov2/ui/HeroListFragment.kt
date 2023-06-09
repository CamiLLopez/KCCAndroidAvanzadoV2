package com.example.entregaandroidavanzadov2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.entregaandroidavanzadov2.databinding.FragmentListBinding
import com.example.entregaandroidavanzadov2.ui.viewModels.HerosViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HerosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SuperHeroAdapter(){ heroID: String ->
            findNavController().navigate(
                HeroListFragmentDirections.actionListFragmentToDetailFragment(
                    heroID
                )
            )
        }
        binding.herosList.adapter = adapter
        viewModel.getHeros()

        viewModel.heros.observe(viewLifecycleOwner){ heros ->
            adapter.submitList(heros)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}