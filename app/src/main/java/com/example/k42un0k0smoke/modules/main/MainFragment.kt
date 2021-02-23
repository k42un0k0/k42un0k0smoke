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

    private val startAlertBuilder by lazy {
        AlertDialog.Builder(activity).apply {
            setTitle("もう煙草を吸えません、本当によろしいですか？")
            setPositiveButton(
                R.string.ok
            ) { _: DialogInterface, _: Int -> viewModel.startTimer() }
            setNegativeButton(R.string.cancel) { _: DialogInterface, _: Int -> }
            create()
        }
    }

    private val stopAlertBuilder by lazy {
        AlertDialog.Builder(activity).apply {
            setTitle("禁煙を終わりますか")
            setPositiveButton(
                R.string.ok
            ) { _: DialogInterface, _: Int -> viewModel.stopTimer() }
            setNegativeButton(R.string.cancel) { _: DialogInterface, _: Int -> }
            create()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.buttonStart.setOnClickListener {
            if (viewModel.isCounting.value == true) {
                stopAlertBuilder.show()
            } else {
                startAlertBuilder.show()
            }
        }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

}