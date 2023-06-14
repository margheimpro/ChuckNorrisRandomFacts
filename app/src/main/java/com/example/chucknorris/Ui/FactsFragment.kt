package com.example.chucknorris.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorris.databinding.FragmentFactsBinding
import com.example.chucknorris.repository.Facts

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

        viewModel.factsLiveData.observe(viewLifecycleOwner) {
            getFacts(it)
        }
   }

    private fun getFacts(facts: Facts){
        binding.textFact.text = facts.value
        binding.chuckPhoto.visibility = View.VISIBLE
    }
}