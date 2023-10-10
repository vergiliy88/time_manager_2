package com.example.time_manager.ui.tasks.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.time_manager.R
import com.example.time_manager.databinding.FragmentEditTaskBinding
import com.example.time_manager.ui.base.BaseFragment
import com.example.time_manager.ui.main.MainActivity
import com.example.time_manager.utils.Utils.Companion.getHoursAndMinFromMin


class EditTasksFragment : BaseFragment<FragmentEditTaskBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(taskId: Long?): EditTasksFragment {
            val fragment = EditTasksFragment()
            val args = Bundle()
            args.putLong("taskId", taskId?:0)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var _viewModal: EditTasksViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModal =
            ViewModelProvider(requireActivity()).get(EditTasksViewModal::class.java)

        _viewModal.setTaskId(arguments?.getLong("taskId"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val mainActivity = activity as MainActivity
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _viewModal.loadTask()

        changeVisibleFAB(true)
        setIconFAB(R.drawable.ic_done_24)

        val adapterPriority = ArrayAdapter<Int>(
            this.requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            _viewModal.priorityList)
        val spinner = binding.taskPrioritySp
        spinner.adapter = adapterPriority

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) { _viewModal.selectedPriority(position)}

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val adapterHours = ArrayAdapter<Int>(
            this.requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            _viewModal.hours)
        val spinnerHours = binding.taskHoursSp
        spinnerHours.adapter = adapterHours

        spinnerHours.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) { _viewModal.selectedHour(position)}

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val adapterMinutes = ArrayAdapter<Int>(
            this.requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            _viewModal.minutes)
        val spinnerMinutes = binding.taskMinutesSp
        spinnerMinutes.adapter = adapterMinutes

        spinnerMinutes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) { _viewModal.selectedMinutes(position)}

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        _viewModal.updateStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                activity?.onBackPressed()
            }
        })

        _viewModal.taskFroDB.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.taskNameEt.setText(it.name)
                val hoursMinutes = getHoursAndMinFromMin(it.expectedTime!!)
                binding.taskHoursSp.setSelection(_viewModal.hours.indexOf(hoursMinutes[0]))
                binding.taskMinutesSp.setSelection(_viewModal.minutes.indexOf(hoursMinutes[1]))
                binding.taskPrioritySp.setSelection(_viewModal.priorityList.indexOf(it.priority))
            }
        })

        _viewModal.taskIdToEdit.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it > 0) {
                    binding.delTaskBtn.visibility = View.VISIBLE
                } else {
                    binding.delTaskBtn.visibility = View.GONE
                }
            }
        })

        binding.delTaskBtn.setOnClickListener {
            _viewModal.delTask()
        }

        mainActivity.fb?.setOnClickListener {
            val name = binding.taskNameEt.text.toString()
            _viewModal.saveTask(name)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setIconFAB(R.drawable.ic_edit_list)
    }
}