package com.example.time_manager.ui.daily_tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.example.time_manager.R
import com.example.time_manager.domain.models.DailyTask
import com.example.time_manager.ui.base.BaseAdapter
import com.example.time_manager.ui.base.BaseViewHolder
import com.example.time_manager.utils.Utils.Companion.convertMinutesTimeToHHMMString

class DailyTaskAdapter (
    listeners: Map<Int, OnViewClickListener>
): BaseAdapter<DailyTask, DailyTaskAdapter.Vh>(
    R.layout.item_daily_task,
    listeners
) {
    class Vh constructor(
        itemView: View,
        viewIds: Set<Int?>?
    ) :
        BaseViewHolder<DailyTask?>(itemView, viewIds) {
        private var name: TextView? = null
        private var time: TextView? = null

        override fun bindView() {
            with(itemView) {
                name = findViewById(R.id.name_task_tv)
                time = findViewById(R.id.time_task_tv)
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bindData(item: DailyTask?) {
            with(item) {
                name?.text =  "${item?.task?.name}"
                time?.text = itemView.context.getString(R.string.spend_time_to_task) + " ${convertMinutesTimeToHHMMString(item?.spendTime, itemView.context)}"
            }
        }
    }

    override fun newVhInstance(view: View?, viewIds: MutableSet<Int>?): Vh {
        return Vh(view!!, viewIds)
    }
}