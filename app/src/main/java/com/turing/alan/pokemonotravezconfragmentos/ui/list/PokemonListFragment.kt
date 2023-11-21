package com.turing.alan.pokemonotravezconfragmentos.ui.list


import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment() {
    private val viewModel:PokemonListViewModel by viewModels()
    //private val viewModel:PokemonListViewModel by activityViewModels()
    private lateinit var binding: FragmentPokemonListBinding

    private lateinit var progressCirculation: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater,
            container,
            false
        )
        progressCirculation = binding.cargando
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonAdapter(::onShowDetail)


        binding.recyclerViewPokemonList.adapter = adapter
        progressCirculation.visibility = View.VISIBLE
        viewModel.pokemonUi.observe(viewLifecycleOwner){
                pokemonList -> adapter.submitList(pokemonList)
                progressCirculation.visibility = View.GONE
        }
    }

    private fun onShowDetail(view:View, pokemon: Pokemon) {
        val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon.name)
        view.findNavController().navigate(action)
    }
}