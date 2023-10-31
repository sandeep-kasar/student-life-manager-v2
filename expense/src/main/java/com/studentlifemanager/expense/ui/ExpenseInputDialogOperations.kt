package com.studentlifemanager.expense.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.FragmentActivity
import com.studentlifemanager.common.data.entity.ExpenseEntity
import com.studentlifemanager.common.utils.DateUtil
import com.studentlifemanager.common.R
import com.studentlifemanager.expense.databinding.LayoutDialogExpenseBinding
import com.studentlifemanager.utils.Constant
import com.studentlifemanager.utils.Constant.DD_MMM_YYYY
import com.studentlifemanager.utils.Constant.DD_MM_YYYY
import com.studentlifemanager.utils.Constant.EMPTY
import com.studentlifemanager.utils.Constant.EXPENSE
import com.studentlifemanager.utils.Constant.INCOME
import com.studentlifemanager.utils.ViewExtensions.gone
import com.studentlifemanager.utils.ViewExtensions.hideKeyboard
import com.studentlifemanager.utils.ViewExtensions.visible
import java.util.Calendar
import java.util.Date

/**
 * This class is used to handle income expense transaction
 * All dialog actions are handle in this class, like add, delete, cancel, select ate etc
 *
 * @author SandeepK
 * @version 2.0
 * */
class ExpenseInputDialogOperations(
    val context: FragmentActivity, private val iExpenseOperation: IExpenseOperation
) {
    /**
     * This dialog is used to add expense and income data
     * here we can add amount,description,notes
     *
     * @param expenseEntity
     */
    fun showDialog(expenseEntity: ExpenseEntity? = null) {

        // int var
        var expenseType = EXPENSE
        var isToUpdate = false

        // init dialog
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = LayoutDialogExpenseBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.CENTER)
        dialog.setCancelable(true)
        dialog.show()

        // check entity is empty or not
        // if empty means user is adding income first time
        // if not empty means user is updating the transaction
        expenseEntity?.let {
            setIncomeExpenseTab(binding, it.exType)
            binding.edtAmount.setText(it.exAmount.toString())
            binding.edtSpendFor.setText(it.exTitle.toString())
            binding.edtNote.setText(it.exNote.toString())
            binding.tvSelectDate.setText(it.exDate.toString())
            binding.tvDelete.visible()
            isToUpdate = true
        } ?: {
            isToUpdate = false
            binding.tvDelete.gone()
        }

        // clicks
        binding.tvExpense.setOnClickListener {
            expenseType = EXPENSE
            setIncomeExpenseTab(binding, expenseType)
        }

        binding.tvIncome.setOnClickListener {
            expenseType = INCOME
            setIncomeExpenseTab(binding, expenseType)
        }

        // set today's date by default
        binding.tvSelectDate.text = DateUtil.getTodayDate()

        // select and set date
        binding.tvSelectDate.setOnClickListener {
            setDate(binding.tvSelectDate)
        }

        // delete transaction
        binding.tvDelete.setOnClickListener {
            iExpenseOperation.deleteData(
                ExpenseEntity(
                    exId = expenseEntity?.exId ?: 0,
                    exAmount = expenseEntity?.exAmount ?: 0,
                    exTitle = expenseEntity?.exTitle ?: EMPTY,
                    exNote = expenseEntity?.exNote ?: EMPTY,
                    exType = expenseType,
                    exDate = expenseEntity?.exDate ?: Date().toString(),
                    exMonth = expenseEntity?.exMonth ?: 0
                )
            )
            dialog.dismiss()
        }

        // on click cancel, close the dialog
        binding.tvCancel.setOnClickListener {
            dialog.cancel()
            context.hideKeyboard()
        }

        // on click ok, save data in expense table
        binding.tvOk.setOnClickListener {

            // hide keyboard after input is done
            context.hideKeyboard()

            // take amount and title
            val amount = binding.edtAmount.text.toString().trim()
            val title = binding.edtSpendFor.text.toString().trim()
            val note = binding.edtNote.text.toString().trim()
            val date = binding.tvSelectDate.text.toString().trim()

            // validate amount and title
            if (amount.isNotBlank() && amount.isDigitsOnly() && title.isNotBlank()) {
                iExpenseOperation.saveOrUpdateData(
                    ExpenseEntity(
                        exId = expenseEntity?.exId ?: 0,
                        exAmount = amount.toLong(),
                        exTitle = title,
                        exNote = note,
                        exType = expenseType,
                        exDate = date,
                        exMonth = expenseEntity?.exMonth ?: 0
                    ), isToUpdate
                )
                dialog.cancel()
            } else {
                if (amount.isBlank()) binding.edtAmount.error =
                    context.getString(R.string.please_add_amount)
                if (title.isBlank()) binding.edtSpendFor.error =
                    context.getString(R.string.please_add_title)
            }
        }
    }

    /**
     * switch income expense tab as per user selection
     *
     * @param binding : dialog layout
     * @param inputType - expense or income type
     */
    private fun setIncomeExpenseTab(binding: LayoutDialogExpenseBinding, inputType: Int) {
        if (inputType == Constant.EXPENSE) {
            binding.tvExpense.setTextColor(context.getColor(R.color.colorPrimaryDark))
            binding.tvExpense.setBackgroundResource(R.drawable.border_square_primary)
            binding.tvIncome.setTextColor(context.getColor(R.color.extra_dark_gray))
            binding.tvIncome.setBackgroundResource(R.drawable.border_square_grey)
        } else if (inputType == Constant.INCOME) {
            binding.tvIncome.setTextColor(context.getColor(R.color.colorPrimaryDark))
            binding.tvIncome.setBackgroundResource(R.drawable.border_square_primary)
            binding.tvExpense.setTextColor(context.getColor(R.color.extra_dark_gray))
            binding.tvExpense.setBackgroundResource(R.drawable.border_square_grey)
        }
    }

    /**
     * DatePicker is called to select expense date
     *
     * @param tvSelectDate : This is the AppCompatTextView of date selection
     */
    private fun setDate(tvSelectDate: AppCompatTextView) {

        // get year,month and date from calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // select date
        val dpd = DatePickerDialog(context, { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in TextView
            val month = monthOfYear + 1
            tvSelectDate.text = DateUtil.formatDateFromString(
                DD_MM_YYYY, DD_MMM_YYYY, "$dayOfMonth-$month-$year"
            )
        }, year, month, day)

        dpd.show()
    }
}