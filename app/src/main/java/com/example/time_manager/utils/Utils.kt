package com.example.time_manager.utils


import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.time_manager.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.floor


class Utils {

    companion object {
        val hours = (0..24).toList().toTypedArray()
        val minutes = (1..59).toList().toTypedArray()
        val priority = arrayOf(1,2,3,4,5)
        fun checkPermissions(
            context: Context?,
            permissions: Array<String>
        ): Array<String?>? {
            requireNotNull(context) { "context не должен быть null" }
            var denied = arrayOfNulls<String>(0)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return denied
            val deniedList: MutableList<String> = ArrayList()
            for (perm in permissions) {
                if (ContextCompat.checkSelfPermission(context, perm)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    deniedList.add(perm)
                }
            }
            denied =  deniedList.toTypedArray()
            return denied
        }

        fun returnToTargetFragment(
            fm: FragmentManager?,
            tag: String?
        ) {
            if (fm == null) {
                return
            }
            val fragment: Fragment = fm.findFragmentByTag(tag)!!
            fragment.let {
                fm.popBackStack(tag, 0)
            }
        }

        fun snack(
            rootView: View?,
            msg: String?,
            duration: Int
        ) {
            snack(
                rootView,
                msg,
                duration,
                0,
                null
            )
        }

        fun snack(
            rootView: View?,
            msg: String?,
            duration: Int,
            @StringRes action: Int,
            runnable: Runnable?
        ) {
            if (rootView == null) return
            val snackbar = Snackbar.make(
                rootView,
                msg!!,
                duration
            )
            val snackView: View = snackbar.view

            snackView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            val tvMessage: TextView = snackView.findViewById(
                com.google.android.material.R.id.snackbar_text
            )

            // увеличен размер текста сообщения
            UiUtils.setTextSize(tvMessage, R.dimen.text_snackbar)
            tvMessage.isSingleLine = false
            tvMessage.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvMessage.setTextColor(
                ResourcesCompat.getColor(rootView.resources, R.color.inverse_text, null)
            )
            snackView.setBackgroundColor(
                snackView
                    .context
                    .resources
                    .getColor(R.color.snackColor)
            )
            if (runnable != null) {
                snackbar.setAction(
                    action
                ) { v: View? -> runnable.run() }
                val tvAction: TextView = snackView.findViewById(
                    com.google.android.material.R.id.snackbar_action
                )
                UiUtils.setTextSize(tvAction, R.dimen.text_snackbar)
                tvAction.isAllCaps = true
            }
            snackbar.show()
        }

        @SuppressLint("SimpleDateFormat")
        fun convertMinutesTimeToHHMMString(minutesTime: Int?, context: Context): String {
            var res: String = ""
            return if (minutesTime != null) {
                if (minutesTime > 60) {
                    val timeZone = TimeZone.getTimeZone("UTC")
                    val df = SimpleDateFormat("HH ${context.getString(R.string.hour)} mm ${context.getString(R.string.min)}")
                    df.timeZone = timeZone
                    res = df.format(Date(minutesTime * 60 * 1000L))
                } else {
                    res = minutesTime.toString() + " ${context.getString(R.string.min)}"
                }
                res
            } else {
                "0 ${R.string.min}"
            }
        }

        fun getHoursAndMinFromMin(minutesTime: Int): Array<Int>{
            if (minutesTime > 60) {
                var hours = (minutesTime / 60).toDouble()
                hours = floor(hours)
                val min = minutesTime - hours.toInt() * 60
                return arrayOf(hours.toInt(), min)
            } else {
                return arrayOf(0, minutesTime)
            }
        }

        fun convertHoursToMinutes(hours: Int, minutes: Int): Int {
            return hours * 60 + minutes
        }
    }
}