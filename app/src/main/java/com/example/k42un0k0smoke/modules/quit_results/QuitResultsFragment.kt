package com.example.k42un0k0smoke.modules.quit_results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k42un0k0smoke.databinding.FragmentQuitResultBinding
import com.example.k42un0k0smoke.model.quit_result.QuitResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuitResultsFragment : Fragment() {
    @Inject
    lateinit var viewModel: QuitResultsViewModel
    private val adapter = MyItemRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuitResultBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        val view = binding.root

        // Set the adapter
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = this.adapter
        }
        viewModel.allQuitResult.observe(viewLifecycleOwner, {
            it.let {
                adapter.submitList(it as MutableList<QuitResult>)
            }
        })
        return view
    }
}