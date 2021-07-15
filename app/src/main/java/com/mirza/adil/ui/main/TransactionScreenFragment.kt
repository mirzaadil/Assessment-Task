package com.mirza.adil.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mirza.adil.databinding.FragmentTransactionScreenBinding
import com.mirza.adil.ui.base.BaseActivity
import com.mirza.adil.ui.base.BaseFragment


class TransactionScreenFragment : BaseFragment<FragmentTransactionScreenBinding>() {

    private val moneyEdit: MutableLiveData<String> = MutableLiveData<String>()
    private val moneyRead: LiveData<String> get() = moneyEdit
    var updatedValue: StringBuilder = StringBuilder()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentTransactionScreenBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setup()

    }


    private fun setup() = with(binding) {
        visibleToolbar()
        etPassCodeText.isFocusable=false
        clickListenerButton()

    }

    /**
     * This function user for button click Listener.
     */
    private fun clickListenerButton()= with(binding) {

        button1.setOnClickListener {
            moneyEdit.value = button1.text.toString() }

        button2.setOnClickListener {
            moneyEdit.value = button2.text.toString()}

        button3.setOnClickListener {
            moneyEdit.value = button3.text.toString() }

        button4.setOnClickListener {
            moneyEdit.value = button4.text.toString() }

        button5.setOnClickListener {
            moneyEdit.value = button5.text.toString() }

        button6.setOnClickListener {
            moneyEdit.value = button6.text.toString() }

        button7.setOnClickListener {
            moneyEdit.value = button7.text.toString() }

        button8.setOnClickListener {
            moneyEdit.value = button8.text.toString() }

        button9.setOnClickListener {
            moneyEdit.value = button9.text.toString() }

        button0.setOnClickListener {
            moneyEdit.value = button0.text.toString() }

        buttonRemove.setOnClickListener {
            if(updatedValue.isNotEmpty()) {
                updatedValue.deleteCharAt(updatedValue.length - 1)
                etPassCodeText?.setText(updatedValue, TextView.BufferType.EDITABLE) } }

        buttonDot.setOnClickListener {
            if(updatedValue.isNotEmpty()) {
                val valueDot = buttonDot.text.toString()
                moneyEdit.value = valueDot } }

        this@TransactionScreenFragment.activity?.let {
            moneyRead.observe(it, {
                if (updatedValue.restrictTwoDots()){
                    updatedValue.append(it) }
                etPassCodeText.setText(updatedValue, TextView.BufferType.EDITABLE)
            })

        }
    }


    /**
     * This function manage application Toolbar
     */
    private fun visibleToolbar() {
        if ((activity as BaseActivity).binding.toolbar.visibility == View.GONE) (activity as BaseActivity).binding.toolbar.visibility =
            View.VISIBLE
    }


    private fun StringBuilder.restrictTwoDots(): Boolean {
        return if (this.contains('.')){
            val valueAfterDot  = this.toString().substringAfter('.')
            valueAfterDot.length <2
        }else{
            true
        }

    }
}