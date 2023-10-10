package com.example.time_manager.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.time_manager.databinding.FragmentChartsBinding
import com.example.time_manager.ui.base.BaseFragment
import com.example.time_manager.ui.tasks.TasksFragment
import com.example.time_manager.ui.tasks.TasksViewModal

class ChartsFragment : BaseFragment<FragmentChartsBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): ChartsFragment {
            return ChartsFragment()
        }
    }

    private lateinit var _viewModal: TasksViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModal =
            ViewModelProvider(requireActivity()).get(TasksViewModal::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentChartsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        changeVisibleFAB(false)

        return root
    }
}