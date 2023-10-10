package com.example.time_manager.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.time_manager.ui.daily_tasks.DailyFragment
import com.example.time_manager.utils.UiUtils
import com.example.time_manager.R
import com.example.time_manager.databinding.ActivityMainBinding
import com.example.time_manager.ui.statistics.ChartsFragment
import com.example.time_manager.ui.tasks.TasksFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModal
    private var toolbar: ActionBar? = null
    var fb: FloatingActionButton? =null


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                MainViewModal::class.java
            )

        toolbar = supportActionBar


        toolbar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFD06F")))
        val navigation: BottomNavigationView = binding.navView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        showSelectedScreen(mainViewModel.positionMenu.value)

        mainViewModel.positionMenu.observe(this, Observer { menuItem ->
            if (menuItem == null) {
                loadFragment(DailyFragment.newInstance())
                toolbar?.setTitle(R.string.title_daily_task)
                mainViewModel.setCurrentMenuId(R.id.navigation_daily_tasks)
            }
        })

        fb = binding.floatingActionButton
    }

    private fun showSelectedScreen(menuId: Int?): Boolean {
        val fragment: Fragment
        var  result = false
        when (menuId) {
            R.id.navigation_daily_tasks -> {
                toolbar?.title = applicationContext.getString(R.string.title_daily_task)
                fragment = DailyFragment.newInstance()
                loadFragment(fragment)
                result = true
            }
            R.id.navigation_charts -> {
                toolbar?.title = applicationContext.getString(R.string.title_charts)
                fragment = ChartsFragment.newInstance()
                loadFragment(fragment)
                result = true
            }
            R.id.navigation_tasks -> {
                toolbar?.title = applicationContext.getString(R.string.title_list_tasks)
                fragment = TasksFragment.newInstance()
                loadFragment(fragment)
                result = true
            }
            R.id.navigation_settings -> {
                toolbar?.title = applicationContext.getString(R.string.title_settings)
                fragment = DailyFragment.newInstance()
                loadFragment(fragment)
                result = true
            }
            null -> {
                toolbar?.title = applicationContext.getString(R.string.title_daily_task)
                fragment = DailyFragment.newInstance()
                loadFragment(fragment)
                result = true
            }
        }
        return result
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            mainViewModel.setCurrentMenuId(item.itemId)
            showSelectedScreen(item.itemId)
        }

    private fun loadFragment(fragment: Fragment) {
        UiUtils.replaceFragment(supportFragmentManager, fragment)
    }

    fun setToolbarTitle(title: SpannableString?) {
        toolbar?.title = title
    }

    fun changeVisibleFAB(state: Boolean){
        if (state) {
            binding.floatingActionButton.show()
        } else {
            binding.floatingActionButton.hide()
        }
    }

}