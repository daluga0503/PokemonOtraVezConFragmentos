package com.turing.alan.pokemonotravezconfragmentos.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment() {
    private val viewModel:PokemonListViewModel by viewModels()
    //private val viewModel:PokemonListViewModel by activityViewModels()
    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater,
            container,
            false

        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonAdapter()
        binding.recyclerViewPokemonList.adapter = adapter

        viewModel.pokemonUi.observe(viewLifecycleOwner){
                pokemonList -> adapter.submitList(pokemonList)
        }
    }
}