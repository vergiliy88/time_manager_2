package com.example.time_manager.ui.tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.example.time_manager.R
import com.example.time_manager.domain.models.Task
import com.example.time_manager.ui.base.BaseAdapter
import com.example.time_manager.ui.base.BaseViewHolder
import com.example.time_manager.utils.Utils.Companion.convertMinutesTimeToHHMMString

class TasksAdapter (
    listeners: Map<Int, OnViewClickListener>
): BaseAdapter<Task, TasksAdapter.Vh>(
    R.layout.item_task,
    listeners
) {
    class Vh constructor(
        itemView: View,
        viewIds: Set<Int?>?
    ) :
        BaseViewHolder<Task?>(itemView, viewIds) {
        private var name: TextView? = null
        private var time: TextView? = null
        private var priority: TextView? = null

        override fun bindView() {
            with(itemView) {
                name = findViewById(R.id.task_name_tv)
                time = findViewById(R.id.task_exp_time)
                priority = findViewById(R.id.task_priority_tv)
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindData(item: Task?) {
            with(item) {
                name?.text =  "${item?.name}"

                time?.text = itemView.context.getString(R.string.expected_time) + " " + convertMinutesTimeToHHMMString(item?.expectedTime, itemView.context)
                priority?.text = itemView.context.getString(R.string.priority) + " ${item?.priority}"
            }
        }
    }

    override fun newVhInstance(view: View?, viewIds: MutableSet<Int>?): Vh {
        return Vh(view!!, viewIds)
    }
}