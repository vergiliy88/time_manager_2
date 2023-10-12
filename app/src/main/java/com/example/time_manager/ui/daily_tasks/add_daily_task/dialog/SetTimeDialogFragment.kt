package com.example.time_manager.ui.daily_tasks.add_daily_task.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.time_manager.R
import com.example.time_manager.utils.UiUtils
import com.example.time_manager.utils.Utils.Companion.convertMinutesTimeToHHMMDotsString
import com.google.android.material.snackbar.Snackbar
import java.util.*

class SetTimeDialogFragment : DialogFragment() {

    private var cal = Calendar.getInstance()
    private lateinit var _viewModal: SetTimeViewModal
    private var mListener: ISetTimeDialog? = null
    lateinit var setStartTimeBtn: Button
    lateinit var setEndTimeBtn: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (mListener == null) {
            mListener = activity as ISetTimeDialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModal = ViewModelProvider(requireActivity()).get(SetTimeViewModal::class.java)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _viewModal.startTime.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null) {
                setStartTimeBtn.text = convertMinutesTimeToHHMMDotsString(it, this.requireContext())
            } else {
                setStartTimeBtn.text = this.requireContext().getText(R.string.hint_time)
            }
        })

        _viewModal.endTime.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null) {
                setEndTimeBtn.text = convertMinutesTimeToHHMMDotsString(it, this.requireContext())
            } else {
                setEndTimeBtn.text = this.requireContext().getText(R.string.hint_time)
            }
        })

        return inflater.inflate(R.layout.fragment_set_time_dialog, null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val detailedForm = requireActivity().layoutInflater.inflate(R.layout.fragment_set_time_dialog, null)


        setStartTimeBtn = detailedForm.findViewById<Button>(R.id.set_start_time_btn)
        setEndTimeBtn = detailedForm.findViewById<Button>(R.id.set_end_time_btn)

        val startTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            run {
//                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
//                cal.set(Calendar.MINUTE, minute)
                _viewModal.setStartTime(hourOfDay, minute)
            }
        }

        setStartTimeBtn.setOnClickListener {
            TimePickerDialog(requireContext(),
                startTimeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true)
                .show()
        }

        val endTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            run {
                _viewModal.setEndTime(hourOfDay, minute)
            }
        }

        setEndTimeBtn.setOnClickListener {
            TimePickerDialog(requireContext(),
                endTimeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true)
                .show()
        }

        val  builder = AlertDialog.Builder(requireActivity())
            .setTitle(R.string.title_set_time)
            .setView(detailedForm)
            .setPositiveButton(R.string.btn_set_data, null)
            .setNegativeButton(R.string.btn_cancel_action, null)
            .setCancelable(false)
            .create()



        builder.setOnShowListener(fun(_: DialogInterface?) {
            builder.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (_viewModal.startTime.value != null && _viewModal.endTime.value != null) {
                    mListener?.onDialogPositiveClick(_viewModal.startTime.value!!, _viewModal.endTime.value!!)
                    dialog?.dismiss()
                } else {
                    UiUtils.snack(
                        this.view,
                        getString(R.string.warning_mess_complete_fields),
                        Snackbar.LENGTH_LONG)
                }
            }
            builder.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
                dialog?.dismiss()
            }
        })
        return builder
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: ISetTimeDialog) =
            SetTimeDialogFragment().apply { mListener = listener }

    }
}