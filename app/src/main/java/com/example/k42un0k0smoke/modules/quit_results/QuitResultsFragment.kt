package com.example.k42un0k0smoke.modules.quit_results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k42un0k0smoke.databinding.FragmentItemBinding
import com.example.k42un0k0smoke.model.QuitResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuitResultsFragment : Fragment() {
    @Inject
    lateinit var viewModel: QuitResultsViewModel
    private var columnCount = 1
    val adapter = MyItemRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val view = binding.root

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = this.adapter
            }
        }
        viewModel.allQuitResult.observe(viewLifecycleOwner, {
            it.let {
                adapter.submitList(it as MutableList<QuitResult>)
            }
        })
        return view
    }
}