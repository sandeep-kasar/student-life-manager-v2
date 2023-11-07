package com.studentlifemanager.expense.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studentlifemanager.common.utils.IRecyclerViewClickListener
import com.studentlifemanager.expense.databinding.LayoutExpBodyBinding
import com.studentlifemanager.expense.databinding.LayoutExpDateBinding
import com.studentlifemanager.expense.databinding.LayoutExpHeaderBinding
import com.studentlifemanager.expense.model.ExpenseData


/**
 * This is the expense adapter, to display expense data
 * data binding is used
 *
 * @author SandeepK
 * @version 2.0
 * */

const val EXP_HEADER = 0
const val EXP_DATE = 1
const val EXP_BODY = 2

class ExpenseAdapter(
    private val iRecyclerViewClickListener: IRecyclerViewClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val expenseData = ArrayList<ExpenseData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            EXP_HEADER -> {
                val binding = LayoutExpHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ExpenseHeaderViewHolder(binding)
            }

            EXP_DATE -> {
                val binding =
                    LayoutExpDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ExpenseDateViewHolder(binding)
            }

            EXP_BODY -> {
                val binding =
                    LayoutExpBodyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ExpenseBodyViewHolder(binding, iRecyclerViewClickListener)
            }

            else -> {
                val binding = LayoutExpHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ExpenseHeaderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = expenseData[position]
        when (getItemViewType(position)) {
            EXP_HEADER -> {
                holder as ExpenseHeaderViewHolder
                holder.bind(item)
            }

            EXP_DATE -> {
                holder as ExpenseDateViewHolder
                holder.bind(item)
            }

            EXP_BODY -> {
                holder as ExpenseBodyViewHolder
                holder.bind(item)
            }

            else -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = expenseData[position]
        when (data.layType) {
            EXP_HEADER -> {
                return EXP_HEADER
            }

            EXP_DATE -> {
                return EXP_DATE
            }

            EXP_BODY -> {
                return EXP_BODY
            }

            else -> {
                return EXP_BODY
            }
        }
    }

    override fun getItemCount(): Int = expenseData.size

    /**
     * This is the view holder class to show total expense, income and balance
     *
     * @param layoutExpHeaderBinding : layout_exp_header
     */
    inner class ExpenseHeaderViewHolder(
        private val layoutExpHeaderBinding: LayoutExpHeaderBinding,
    ) : RecyclerView.ViewHolder(layoutExpHeaderBinding.root) {
        fun bind(expenseData: ExpenseData) {
            layoutExpHeaderBinding.item = expenseData.expenseHeader
        }
    }

    /**
     * This is the view holder class to date
     * and total transactions in same date
     *
     * @param layoutExpDateBinding : layout_exp_date
     */
    inner class ExpenseDateViewHolder(
        private val layoutExpDateBinding: LayoutExpDateBinding,
    ) : RecyclerView.ViewHolder(layoutExpDateBinding.root) {
        fun bind(expenseData: ExpenseData) {
            layoutExpDateBinding.item = expenseData.expenseDate
        }
    }

    /**
     * This is the view holder class to show income or expenses transactions
     *
     * @param layoutExpBodyBinding : layout_exp_body
     */
    inner class ExpenseBodyViewHolder(
        private val layoutExpBodyBinding: LayoutExpBodyBinding,
        private val iRecyclerViewClickListener: IRecyclerViewClickListener
    ) : RecyclerView.ViewHolder(layoutExpBodyBinding.root) {
        fun bind(expenseData: ExpenseData) {
            layoutExpBodyBinding.item = expenseData.expenseEntity
            layoutExpBodyBinding.callBack = iRecyclerViewClickListener
        }
    }

    /**
     * Set Expense data to adapter
     *
     * @param data : array list of ExpenseData
     */
    fun setData(data: ArrayList<ExpenseData>) {
        this.expenseData.clear()
        this.expenseData.addAll(data)
        notifyDataSetChanged()
    }
}