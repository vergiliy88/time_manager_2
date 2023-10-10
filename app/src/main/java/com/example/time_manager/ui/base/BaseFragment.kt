package com.example.time_manager.ui.base


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.time_manager.ui.main.MainActivity
import com.example.time_manager.utils.UiUtils


open class BaseFragment<VB>: Fragment() {

    var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View(this.context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun setToolBarTitle(
        value: String?,
        activity: Activity?
    ) {
        if (activity is MainActivity) {
            activity.setToolbarTitle(UiUtils.setBoldValueInBKT(value))
        }
    }

    open fun changeVisibleFAB(state: Boolean) {
        if (activity is MainActivity) {
            (activity as MainActivity).changeVisibleFAB(state)
        }
    }

    open fun setIconFAB(icon: Int) {
        if (activity is MainActivity) {
            (activity as MainActivity).fb?.setImageResource(icon)
        }
    }

}