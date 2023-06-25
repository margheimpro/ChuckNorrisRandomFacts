package com.example.chucknorris.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorris.databinding.FragmentFactsBinding

class FactsFragment : Fragment() {

    private lateinit var binding: FragmentFactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFactsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(FactsViewModel::class.java)

        binding.buttonFact.setOnClickListener {
            viewModel.generateFact()
        }

        viewModel.factLiveData.observe(viewLifecycleOwner) {
            binding.textFact.text = it.value
            binding.chuckPhoto.visibility = View.VISIBLE
        }
   }

}