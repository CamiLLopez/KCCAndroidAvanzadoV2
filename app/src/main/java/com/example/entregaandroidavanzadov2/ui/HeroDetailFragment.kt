package com.example.entregaandroidavanzadov2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.entregaandroidavanzadov2.LocationsHero
import com.example.entregaandroidavanzadov2.R
import com.example.entregaandroidavanzadov2.databinding.FragmentDetailBinding
import com.example.entregaandroidavanzadov2.ui.viewModels.HerosDetailViewModel
import com.example.entregaandroidavanzadov2.ui.viewModels.HerosViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class HeroDetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentDetailBinding? = null
    private lateinit var mMap: GoogleMap

    private val binding get() = _binding!!
    private val viewModel: HerosDetailViewModel by viewModels()
    private val args: HeroDetailFragmentArgs by navArgs()
    private var locations: List<LocationsHero>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.heros_map) as SupportMapFragment
        mapFragment.getMapAsync(this)


         val heroID = args.heroId

        viewModel.getHero(heroID)
        viewModel.getLocationsByHero(heroID)

        viewModel.heroResult.observe(viewLifecycleOwner){hero->

            binding.heroNameDetail.text = hero.name
            binding.heroDetail.text = hero.description
            binding.favoriteHero.isChecked = hero.favorite

            Picasso
                .get()
                .load(hero.photo)
                .resize(1040, 600)
                .centerInside()
                .into(binding.heroNamePhoto)
        }

        viewModel.locationResult.observe(viewLifecycleOwner) { success ->

            Log.d("Locations success", success.toString())
            if (success != null) {

                locations = success
                Log.d("Locations locations", locations.toString())
                updateMap()
            }
        }

        binding.favoriteHero.setOnClickListener{

            viewModel.markFavoriteHero(heroID, binding.favoriteHero.isChecked)
        }

    }

    private fun updateMap() {

        lifecycleScope.launch(Dispatchers.Main){

             locations?.map {


                 val latLng = LatLng(it.latitud, it.longitud)

                 mMap.addMarker(
                     MarkerOptions()
                         .position(latLng)
                         .title("I was here!")
                 )
                mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng, 5.0f))
             }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        updateMap()

    }
}