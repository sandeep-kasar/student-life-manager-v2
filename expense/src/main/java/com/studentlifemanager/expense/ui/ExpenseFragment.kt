package com.studentlifemanager.expense.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.studentlifemanager.common.data.entity.ExpenseEntity
import com.studentlifemanager.common.utils.Constant
import com.studentlifemanager.expense.R
import com.studentlifemanager.expense.databinding.FragmentMyexpenseBinding
import com.studentlifemanager.expense.utils.IViewCallback
import com.studentlifemanager.expense.utils.MonthYearPickerDialog
import com.studentlifemanager.utils.IRecyclerViewClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Calendar
import java.util.Date


/**
 * Fragment which deals with all expense related task
 * like display expense, add expense, show summary etc
 *
 *
 * @author SandeepK
 * @version 2.0
 * */

@AndroidEntryPoint
class ExpenseFragment : Fragment(), IRecyclerViewClickListener,
    IExpenseOperation, IViewCallback {

    private var mMonth: Int = 1

    private val expenseViewModel by viewModels<ExpenseViewModel>()
    private lateinit var _binding: FragmentMyexpenseBinding
    private lateinit var expenseAdapter: ExpenseAdapter

    init {
        // select current month for future
        mMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyexpenseBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.callback = this
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup adapter, recyclerview
        setupUI()

        // observe selected month and added income expense transaction
        setupObserver()

        //set menu
        setHasOptionsMenu(true)
    }

    /**
     * setup UI,
     * Init adapter, recyclerView
     * get data from database and display on screen
     *
     * */
    private fun setupUI() {

        // adapter setting
        expenseAdapter = ExpenseAdapter(this)
        _binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.setHasFixedSize(true)
            it.adapter = expenseAdapter
        }

        // below toolbar, date and year is visible by default we are showing current year nad month
        // this function is used for this purpose
        setDefaultDate()

    }

    /**
     * Observe user selected month and added transaction
     *
     * selectedMonth, expenseData
     */
    private fun setupObserver() {

        expenseViewModel.getExpenseData(mMonth)

        expenseViewModel.selectedMonth.observe(viewLifecycleOwner) { month ->
            mMonth = month
            expenseViewModel.getExpenseData(mMonth)
        }

        expenseViewModel.expenseData.observe(viewLifecycleOwner) {
            expenseAdapter.setData(it)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_expense, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                //open search activity
                //startActivity(Intent(activity, SearchExpenseActivity::class.java))
                return true
            }

            else -> {}
        }
        return true
    }


    /**
     * call back for user click on item row. i.e expense income transaction
     */
    override fun <T> onItemClick(data: T) {
        data as ExpenseEntity
        ExpenseInputDialogOperations(requireActivity(), this).showDialog(data)
    }

    /**
     * In this function, Have inserted data in my expense table
     * coroutine is used for insertion
     *
     * @param expenseEntity
     */
    override fun saveOrUpdateData(expenseEntity: ExpenseEntity, isToUpdate: Boolean) {

        // get current month
        if (mMonth == 1) mMonth = Calendar.getInstance().get(Calendar.MONTH)

        // create object of my expense entity
        val myExpenseEntity = ExpenseEntity(
            exId = expenseEntity.exId,
            exAmount = expenseEntity.exAmount,
            exTitle = expenseEntity.exTitle,
            exNote = expenseEntity.exNote,
            exType = expenseEntity.exType,
            exDate = expenseEntity.exDate,
            exMonth = mMonth
        )

        if (isToUpdate) {
            // update table : my_expense
            expenseViewModel.updateExpenseById(myExpenseEntity)
        } else {
            // insert in table : my_expense
            expenseViewModel.insert(myExpenseEntity)
        }

    }

    /**
     * call back function to delete data from my_expense table
     */
    override fun deleteData(expenseEntity: ExpenseEntity) {
        expenseViewModel.deleteExpenseById(expenseEntity)
    }

    /**
     * show expense input dialog
     */
    override fun viewCallBack() {
        ExpenseInputDialogOperations(requireActivity(), this).showDialog()
    }

    /**
     * This is call back function for view click in order to select month or year
     *
     */
    override fun onClickDate() {
        selectDate()
    }

    /**
     * When user click on month or year this function will open a date picker dialog
     * Here user can select year or month in order to get respective expenses
     */
    private fun selectDate() {
        MonthYearPickerDialog(Date()).apply {
            setListener { view, year, month, dayOfMonth ->
                expenseViewModel.selectItem(month + 1)
                _binding.tvMonth.text = getMonth()[month]
                _binding.tvYear.text = year.toString()
            }
            activity?.supportFragmentManager?.let { show(it, Constant.DATE_PICKER_TAG) }
        }
    }

    /**
     * below toolbar, date and year is visible
     * by default we are showing current year nad month
     *
     * this function is used for this purpose
     */
    private fun setDefaultDate() {
        val monthFormat: DateFormat = SimpleDateFormat(Constant.FULL_MONTH)
        val yearFormat: DateFormat = SimpleDateFormat(Constant.FULL_YEAR)
        val date = Date()
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        _binding.tvMonth.text = monthFormat.format(date)
        _binding.tvYear.text = yearFormat.format(date)
        expenseViewModel.selectItem(localDate.monthValue)
    }

}