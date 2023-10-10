package com.example.time_manager.ui.daily_tasks


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.time_manager.R
import com.example.time_manager.ui.base.BaseFragment
import com.example.time_manager.databinding.FragmentDailyTasksBinding
import com.example.time_manager.ui.base.BaseAdapter
import com.example.time_manager.ui.daily_tasks.add_daily_task.AddDailyTaskFragment
import com.example.time_manager.ui.main.MainActivity
import com.example.time_manager.ui.tasks.edit.EditTasksFragment
import com.example.time_manager.utils.UiUtils

class DailyFragment : BaseFragment<FragmentDailyTasksBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): DailyFragment {
            return DailyFragment()
        }
    }

    private lateinit var _viewModal: DailyViewModal
    private lateinit var adapter: DailyTaskAdapter
    private lateinit var dailyTaskList: RecyclerView
    private val TAG_FR = "ADD_TASK"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModal =
            ViewModelProvider(requireActivity()).get(DailyViewModal::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val mainActivity = activity as MainActivity

        _binding = FragmentDailyTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dailyTaskList = _binding!!.dailyTaskRl

        val listeners = mapOf(
            R.id.item_daily_task_ll to BaseAdapter.OnViewClickListener { _, value ->
                val list = _viewModal._tasksOfDay.value
                val position = value as Int
                list?.get(position)?.let {
                    _viewModal.getApi()
                }
            }
        )

        adapter = DailyTaskAdapter(listeners)
        dailyTaskList.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
        _viewModal._tasksOfDay.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.clear()
                adapter.populate(it)
            }
        })

        mainActivity.fb?.setOnClickListener {
            val fragment = AddDailyTaskFragment.newInstance()
            UiUtils.replaceFragment(parentFragmentManager, fragment, TAG_FR)
        }
//        if(_viewModal._tasksOfDay.value!!.size == 0) {
//            _viewModal.getTasks()
//        }

        changeVisibleFAB(true)
        return root
    }
}