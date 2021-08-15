package com.studentlifemanager.ui.expense

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.studentlifemanager.R
import com.studentlifemanager.data.database.AppRoomDatabase
import com.studentlifemanager.data.entity.MyExpenseEntity
import com.studentlifemanager.databinding.FragmentMyexpenseBinding
import com.studentlifemanager.utils.Constant
import com.studentlifemanager.utils.DateUtil
import com.studentlifemanager.utils.ViewExtensions.getColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


/**
 * Fragment which deals with all expense related task
 * like display expense, add expense, show summary etc
 *
 * @author SandeepK
 * @version 2.0
 * */

class ExpenseFragment : Fragment() {

    private lateinit var dashboardViewModel: ExpenseViewModel
    private var _binding: FragmentMyexpenseBinding? = null
    private val binding get() = _binding!!
    private lateinit var roomDatabase: AppRoomDatabase
    private val repository by lazy { MyExpenseRepository(roomDatabase) }

    private var mMonth : Int = 1

    companion object{
        private var INPUT_TYPE = Constant.EXPENSE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyexpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init
        dashboardViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        roomDatabase = context?.applicationContext?.let { AppRoomDatabase.getDatabase(it) }!!

        // clicks
        _binding?.fab?.setOnClickListener { view ->
            showDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * DatePicker is called to select expense date
     *
     * @param tvSelectDate : This is the AppCompatTextView of date selection
     */
   private fun setDate(tvSelectDate: AppCompatTextView){

        // get year,month and date from calendar
       val c = Calendar.getInstance()
       val year = c.get(Calendar.YEAR)
       val month = c.get(Calendar.MONTH)
       val day = c.get(Calendar.DAY_OF_MONTH)

       // select date
       val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
           // Display Selected date in TextView
           mMonth = monthOfYear+1
           tvSelectDate.text = DateUtil.formatDateFromString(
               "dd-MM-yyyy",
               "dd MMM yyyy",
               "$dayOfMonth-$mMonth-$year")
       }, year, month, day)

       dpd.show()
   }

    private fun saveData(amount:Long,title:String,note:String,date:String){

        // create object of my expense entity
        val myExpenseRepository =MyExpenseEntity(amount,title,note, INPUT_TYPE,date,mMonth)

        // insert my expense data in table : my_expense
        lifecycleScope.launch(Dispatchers.IO){
            repository.insert(myExpenseEntity = myExpenseRepository)
        }
    }

    /**
     * This dialog is used to add expense and income data
     * here we can add amount,description,notes
     * google voice command is also added
     *
     */
    private fun showDialog() {

        // init dialog
        val dialog = Dialog(requireActivity())
        val window = dialog.window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_dialog_expense)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window!!.setGravity(Gravity.CENTER)
        dialog.setCancelable(true)
        dialog.show()

        //init views
        val tvExpense = dialog.findViewById<View>(R.id.tvExpense) as AppCompatTextView
        val tvIncome = dialog.findViewById<View>(R.id.tvIncome) as AppCompatTextView
        val tvCancel = dialog.findViewById<View>(R.id.tvCancel) as AppCompatTextView
        val tvOk = dialog.findViewById<View>(R.id.tvOk) as AppCompatTextView
        var tvSelectDate = dialog.findViewById<View>(R.id.tvSelectDate) as AppCompatTextView
        var edtAmount = dialog.findViewById<View>(R.id.edtAmount) as AppCompatEditText
        var edtSpendFor = dialog.findViewById<View>(R.id.edtSpendFor) as AppCompatEditText
        var edtNote = dialog.findViewById<View>(R.id.edtNote) as AppCompatEditText

        // clicks
        tvExpense.setOnClickListener {
            INPUT_TYPE = Constant.EXPENSE
            edtSpendFor.setHint(getString(R.string.hint_spend_for))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvExpense.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorPrimaryDark))
                tvExpense.setBackgroundResource(R.drawable.border_square_primary)
                tvIncome.setTextColor(ContextCompat.getColor(requireContext(),R.color.extra_dark_gray))
                tvIncome.setBackgroundResource(R.drawable.border_square_grey)
            }else{
                tvExpense.setTextColor(resources.getColor(R.color.colorPrimaryDark))
                tvExpense.setBackgroundResource(R.drawable.border_square_primary)
                tvIncome.setTextColor(resources.getColor(R.color.extra_dark_gray))
                tvIncome.setBackgroundResource(R.drawable.border_square_grey)
            }
        }

        tvIncome.setOnClickListener {
            INPUT_TYPE = Constant.INCOME
            edtSpendFor.setHint(getString(R.string.hint_source))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tvIncome.setTextColor(getColor(R.color.colorPrimaryDark))
                tvIncome.setBackgroundResource(R.drawable.border_square_primary)
                tvExpense.setTextColor(getColor(R.color.extra_dark_gray))
                tvExpense.setBackgroundResource(R.drawable.border_square_grey)
            }else{
                tvIncome.setTextColor(getColor(R.color.colorPrimaryDark))
                tvIncome.setBackgroundResource(R.drawable.border_square_primary)
                tvExpense.setTextColor(getColor(R.color.extra_dark_gray))
                tvExpense.setBackgroundResource(R.drawable.border_square_grey)
            }
        }

        // set today's date by default
        tvSelectDate.text = DateUtil.getTodayDate()

        // select and set date
        tvSelectDate.setOnClickListener {
           setDate(tvSelectDate)
        }

        // on click cancel close the dialog
        tvCancel.setOnClickListener {
            dialog.cancel()
        }

        // on click ok save data in expense table
        tvOk.setOnClickListener {
            dialog.cancel()
            saveData(
                edtAmount.text.toString().toLong(),
                edtSpendFor.text.toString().trim(),
                edtNote.text.toString().trim(),
                tvSelectDate.text.toString().trim(),
            )
        }

        /*//get widgets
        edt_amount = dialog.findViewById<View>(R.id.edt_amount) as EditText
        edt_source = dialog.findViewById<View>(R.id.edt_source) as EditText
        edt_add_note = dialog.findViewById<View>(R.id.edt_add_note) as EditText
        tv_date = dialog.findViewById<View>(R.id.tv_date) as TextView
        val btn_cancel = dialog.findViewById<View>(R.id.btn_cancel) as Button
        val btn_ok = dialog.findViewById<View>(R.id.btn_ok) as Button
        val img_speak = dialog.findViewById<View>(R.id.img_speak) as ImageView

        //set source
        if (type == 1 || type == 2) {
            edt_source.setHint("Source")
        }

        //set date
        tv_date.setText(CommonUtils.setTodaysDate())

        //cancel dialog
        btn_cancel.setOnClickListener { //hide float
            menuInput.close(true)
            //dismiss dialog
            dialog.dismiss()
            //hide keyboard
            val imm =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        }

        //add data in database
        btn_ok.setOnClickListener {
            //hide float
            menuInput.close(true)
            val amount: String = edt_amount.getText().toString()
            val title: String = edt_source.getText().toString()
            val note: String = edt_add_note.getText().toString()

            //check date is selected or not
            if (date == null) {
                date = CommonUtils.getTodaysDate()
            }
            if (amount != "" && title != "") {

                //clear old data
                objectList.clear()

                //get expense amount in int format
                val exp_amount = amount.toInt()

                //add data in database
                myExpenseOperation.insertExpence(title, note, exp_amount, type, date)

                //set data
                setData()
                Toast.makeText(activity, "Added in list successfully", Toast.LENGTH_LONG).show()

                //dismiss dialog
                dialog.dismiss()

                //hide keyboard
                val imm =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
            }
        }

        //get input date
        tv_date.setOnClickListener(View.OnClickListener { showDatePicker() })
        img_speak.setOnClickListener { promptSpeechInput("Say something..") }
        edt_amount.requestFocus()
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
*/    }
}