package com.example.time_manager.ui.daily_tasks.add_daily_task


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.time_manager.R
import com.example.time_manager.databinding.FragmentAddDailyTaskBinding
import com.example.time_manager.ui.base.BaseFragment
import com.example.time_manager.ui.main.MainActivity


class AddDailyTaskFragment : BaseFragment<FragmentAddDailyTaskBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): AddDailyTaskFragment {
            return AddDailyTaskFragment()
        }
    }

    private lateinit var _viewModal: AddDailyTaskViewModal
    private lateinit var taskSpinnerAdapter: SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModal =
            ViewModelProvider(requireActivity()).get(AddDailyTaskViewModal::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val mainActivity = activity as MainActivity
        _binding = FragmentAddDailyTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        changeVisibleFAB(true)
        setIconFAB(R.drawable.ic_done_24)

        taskSpinnerAdapter = SpinnerAdapter(this.requireContext(), listOf())
        val spinnerHours = binding.addDailyTaskPrioritySp
        spinnerHours.adapter = taskSpinnerAdapter

        _viewModal.tasks.observe(viewLifecycleOwner, Observer{
            taskSpinnerAdapter = SpinnerAdapter(this.requireContext(), it)
            spinnerHours.adapter = taskSpinnerAdapter
        })

        spinnerHours.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                _viewModal.setTask(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.inputTimeBtn.setOnClickListener {

        }

        setToolBarTitle(this.requireContext().getString(R.string.title_add_task), this.requireActivity())

        mainActivity.fb?.setOnClickListener {

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setIconFAB(R.drawable.ic_edit_list)
    }
}