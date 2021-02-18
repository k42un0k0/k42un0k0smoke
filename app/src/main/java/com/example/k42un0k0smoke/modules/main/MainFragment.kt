package com.example.k42un0k0smoke.modules.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.k42un0k0smoke.R
import com.example.k42un0k0smoke.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.buttonStart.setOnClickListener {
            if (viewModel.isCounting.value == true) {
                showAlert()
            } else {
                viewModel.startTimer()
            }
        }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(activity)
        builder.apply {
            setTitle("禁煙を終わりますか")
            setPositiveButton(
                R.string.ok
            ) { d: DialogInterface, i: Int -> viewModel.stopTimer() }
            setNegativeButton(R.string.cancel) { d: DialogInterface, i: Int -> }
        }
        builder.create()
        builder.show()
    }

}