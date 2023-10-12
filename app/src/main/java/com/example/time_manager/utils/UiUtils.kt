package com.example.time_manager.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.time_manager.R
import com.google.android.material.snackbar.Snackbar


class UiUtils {
    companion object {
        fun replaceFragment(
            fm: FragmentManager?,
            fragment: Fragment?
        ) {
            replaceFragment(fm, fragment, false, null)
        }

        fun replaceFragment(
            fm: FragmentManager?,
            fragment: Fragment?,
            backStackTag: String?
        ) {
            replaceFragment(fm, fragment, true, backStackTag)
        }

        fun replaceFragment(
            fm: FragmentManager?,
            fragment: Fragment?,
            backStack: Boolean,
            backStackTag: String?
        ) {

            if (fm == null ||
                fragment == null
            ) {
                return
            }
            val transaction: FragmentTransaction = fm.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .replace(R.id.nav_host_fragment_activity_main, fragment, backStackTag)
            if (backStack) transaction.addToBackStack(backStackTag)
            transaction.commit()
        }

        fun setBoldValueInBKT(text: String?): SpannableString? {
            val string = SpannableString(text)
            return setBoldValueInBkt(string)
        }

        private fun setBoldValueInBkt(
            spannableString: SpannableString
        ): SpannableString? {
            val text = spannableString.toString()
            val startBold: Int = text.indexOf(Constants.OPEN_BKT) + 1
            val endBold: Int = text.indexOf(Constants.CLOSE_BKT)
            return if (startBold == 0
                || endBold == Constants.DEFAULT_RESULT_CODE
            ) {
                spannableString
            } else {
                spannableString.setSpan(
                    StyleSpan(Typeface.BOLD),
                    startBold,
                    endBold,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableString
            }
        }

        fun setTextSize(
            tv: TextView?,
            @DimenRes textSize: Int
        ) {
            tv?.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                tv.context.resources
                    .getDimension(textSize)
            )
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
            val snackView = snackbar.view


            snackView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            val tvMessage = snackView.findViewById<TextView>(
                com.google.android.material.R.id.snackbar_text
            )

            // увеличен размер текста сообщения
            setTextSize(tvMessage, R.dimen.text_snackbar)
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
                val tvAction = snackView.findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_action
                )
                setTextSize(tvAction, R.dimen.text_snackbar)
                tvAction.isAllCaps = true
            }
            snackbar.show()
        }


    }

}