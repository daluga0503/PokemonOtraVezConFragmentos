package com.turing.alan.pokemonotravezconfragmentos.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.turing.alan.pokemonotravezconfragmentos.R
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonDetailBinding
import androidx.navigation.fragment.findNavController
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding

class PokemonDetailFragment : Fragment() {
    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailBinding

    val observer = Observer<Pokemon>{
        binding.toolbar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.pokemonListFragment, false)
        }
        binding.pokemonName.text = it.name
        binding.pokemonImg.load(it.imgDetail)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(args.name)
        viewModel.pokemonUi.observe(viewLifecycleOwner,observer)
    }
}