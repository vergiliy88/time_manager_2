package com.example.time_manager.ui.daily_tasks.add_daily_task



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.time_manager.R
import com.example.time_manager.domain.models.Task


class SpinnerAdapter(internal var context: Context, var tasks: List<Task>) :
    BaseAdapter() {
    private var inflter: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(i: Int): Any? {
        return tasks[i]
    }

    override fun getItemId(i: Int): Long {
        return tasks[i].id!!
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {

        val view = inflter.inflate(R.layout.spinner_item_task,null)
        val taskName = view.findViewById<View>(R.id.spinner_task_name_tv) as TextView?
        val taskPrior = view.findViewById<View>(R.id.spinner_task_priority_tv) as TextView?
        taskPrior!!.text = tasks[i].priority.toString()
        taskName!!.text = tasks[i].name
        return view
    }
}