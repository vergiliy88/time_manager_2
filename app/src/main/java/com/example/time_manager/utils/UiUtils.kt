package com.example.time_manager.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.time_manager.R


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
    }

}