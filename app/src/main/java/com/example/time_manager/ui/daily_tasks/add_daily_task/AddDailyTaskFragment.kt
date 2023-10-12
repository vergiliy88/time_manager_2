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
import com.example.time_manager.ui.daily_tasks.DailyFragment
import com.example.time_manager.ui.daily_tasks.add_daily_task.dialog.ISetTimeDialog
import com.example.time_manager.ui.daily_tasks.add_daily_task.dialog.SetTimeDialogFragment
import com.example.time_manager.ui.main.MainActivity
import com.example.time_manager.ui.tasks.edit.EditTasksFragment
import com.example.time_manager.utils.UiUtils
import com.example.time_manager.utils.Utils.Companion.convertMinutesTimeToHHMMString
import com.google.android.material.snackbar.Snackbar


class AddDailyTaskFragment : BaseFragment<FragmentAddDailyTaskBinding>(), ISetTimeDialog {

    companion object {
        @JvmStatic
        fun newInstance(): AddDailyTaskFragment {
            return AddDailyTaskFragment()
        }
    }

    private lateinit var _viewModal: AddDailyTaskViewModal
    private lateinit var taskSpinnerAdapter: SpinnerAdapter
    private val TAG_FR = "EDIT_TASK"

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
        val spinnerTasks = binding.addDailyTaskPrioritySp
        spinnerTasks.adapter = taskSpinnerAdapter

        _viewModal.tasks.observe(viewLifecycleOwner, Observer{
            if (it.isEmpty()) {
                binding.containerWarningLl.visibility = View.VISIBLE
            } else {
                binding.containerWarningLl.visibility = View.GONE
                taskSpinnerAdapter = SpinnerAdapter(this.requireContext(), it)
                spinnerTasks.adapter = taskSpinnerAdapter
            }

        })

        _viewModal.expendTime.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.spendTimeTv.text =
                    "${this.requireContext().getString(R.string.spend_time_to_task)} ${convertMinutesTimeToHHMMString(it, this.requireContext())}"
            }
        })

        spinnerTasks.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        _viewModal.message.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it){
                    AddDailyTaskViewModal.STATUS.ERROR_TIMER -> {
                        UiUtils.snack(
                            this.view,
                            getString(R.string.warning_set_time),
                            Snackbar.LENGTH_LONG)
                    }
                    AddDailyTaskViewModal.STATUS.ERROR_TASK -> {
                        UiUtils.snack(
                            this.view,
                            getString(R.string.warning_task_select),
                            Snackbar.LENGTH_LONG)
                    }
                }
            }
        })

        _viewModal.lastInsertTask.observe(viewLifecycleOwner, Observer {
            it?.let{
                val fragment = DailyFragment.newInstance()

                UiUtils.replaceFragment(parentFragmentManager, fragment, TAG_FR)
            }
        })

        binding.addTaskImg.setOnClickListener {
            val fragment = EditTasksFragment.newInstance(null)
            UiUtils.replaceFragment(parentFragmentManager, fragment, TAG_FR)
        }

        binding.inputTimeBtn.setOnClickListener {
            _viewModal.toggleTimer(0)
            val setTimeDialogFM = SetTimeDialogFragment.newInstance(this)
            setTimeDialogFM.show(this.parentFragmentManager, "SET_TIME")
        }

        binding.startTimerBtn.setOnClickListener {
            _viewModal.setManualTime(0, 0)
        }

        setToolBarTitle(this.requireContext().getString(R.string.title_add_task), this.requireActivity())

        mainActivity.fb?.setOnClickListener {
            _viewModal.saveDailyTask()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setIconFAB(R.drawable.ic_edit_list)
    }

    override fun onDialogPositiveClick(startTime: Int, endTime: Int) {
        _viewModal.setManualTime(startTime, endTime)
    }

    override fun onDialogNegativeClick() {

    }
}