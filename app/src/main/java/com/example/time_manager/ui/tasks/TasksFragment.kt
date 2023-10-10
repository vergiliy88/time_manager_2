package com.example.time_manager.ui.tasks

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
import com.example.time_manager.databinding.FragmentTasksBinding
import com.example.time_manager.ui.base.BaseAdapter
import com.example.time_manager.ui.base.BaseFragment
import com.example.time_manager.ui.daily_tasks.DailyTaskAdapter
import com.example.time_manager.ui.main.MainActivity
import com.example.time_manager.ui.tasks.edit.EditTasksFragment
import com.example.time_manager.utils.UiUtils

class TasksFragment : BaseFragment<FragmentTasksBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(): TasksFragment {
            return TasksFragment()
        }
    }

    private lateinit var _viewModal: TasksViewModal
    private lateinit var taskList: RecyclerView
    private lateinit var adapter: TasksAdapter
    private val TAG_FR = "EDIT_TASK"

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
        val mainActivity = activity as MainActivity
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        taskList = binding.taskListRv

        val listeners = mapOf(
            R.id.item_list_task_cl to BaseAdapter.OnViewClickListener { _, value ->
                val list = _viewModal.tasks.value
                val position = value as Int
                list?.get(position)?.let {
                    val fragment = EditTasksFragment.newInstance(list[position].id)
                    UiUtils.replaceFragment(parentFragmentManager, fragment, TAG_FR)
                }
            }
        )

        adapter = TasksAdapter(listeners)
        taskList.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        _viewModal.tasks.observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter.clear()
                adapter.populate(it)
            }
        })

        mainActivity.fb?.setOnClickListener {
            val fragment = EditTasksFragment.newInstance(null)
            UiUtils.replaceFragment(parentFragmentManager, fragment, TAG_FR)
        }

        changeVisibleFAB(true)
        return root
    }
}