package com.example.chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.chucknorris.databinding.ActivityMainBinding
import com.example.chucknorris.repository.ChuckApiFacts
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var factApi: ChuckApiFacts



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        factApi = retrofit.create(ChuckApiFacts::class.java)


        binding.buttonFact.setOnClickListener {
            generateFact()
        }
    }

    private fun generateFact() {
        lifecycleScope.launch {
            try {
                val factsList = factApi.getFact()
                if (factsList.value == null) {
                    Log.d("MainActivity", "null")
                    generateFact()
                } else {
                    Log.d("MainActivity", "not null")
                    binding.textFact.text = factsList.value
                    binding.chuckPhoto.isVisible = true
                }
            } catch (e: java.lang.Exception) {
                Snackbar.make(
                    findViewById(R.id.main_view),
                    getString(R.string.error_text),
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(getString(R.string.retry_text)) { generateFact() }.show()
            }
        }
    }

}