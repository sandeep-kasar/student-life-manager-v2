package com.studentlifemanager.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studentlifemanager.R
import com.studentlifemanager.databinding.ActivityMainBinding
import com.studentlifemanager.utils.Constant.DATE_PICKER_TAG
import com.studentlifemanager.utils.Constant.FULL_MONTH
import com.studentlifemanager.utils.Constant.FULL_YEAR
import com.studentlifemanager.utils.IViewCallback
import com.studentlifemanager.utils.MonthYearPickerDialog
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date

/**
 * This is the main activity of the app
 * All other fragments are implemented inside this activity
 *
 *
 * @author SandeepK
 * @version 2.0
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IViewCallback {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        //setup bottom navigation
        binding.navView.setupWithNavController(
            findNavController(R.id.nav_host_fragment_activity_main)
        )

        // below toolbar, date and year is visible by default we are showing current year nad month
        // this function is used for this purpose
        setDefaultDate()

    }

    /**
     * This is call back function for view click in order to select month or year
     *
     */
    override fun viewCallBack() {
        selectMonth()
    }


    /**
     * When user click on month or year this function will open a date picker dialog
     * Here user can select year or month in order to get respective expenses
     */
    private fun selectMonth() {
        MonthYearPickerDialog(Date()).apply {
            setListener { view, year, month, dayOfMonth ->
                mainViewModel.selectItem(month + 1)
                binding.tvMonth.text = getMonth()[month]
                binding.tvYear.text = year.toString()
            }
            show(supportFragmentManager, DATE_PICKER_TAG)
        }
    }

    /**
     * below toolbar, date and year is visible
     * by default we are showing current year nad month
     *
     * this function is used for this purpose
     */
    private fun setDefaultDate() {
        val monthFormat: DateFormat = SimpleDateFormat(FULL_MONTH)
        val yearFormat: DateFormat = SimpleDateFormat(FULL_YEAR)
        val date = Date()
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        binding.tvMonth.text = monthFormat.format(date)
        binding.tvYear.text = yearFormat.format(date)
        mainViewModel.selectItem(localDate.monthValue)
    }

}