package com.mirza.adil.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mirza.adil.databinding.FragmentTransactionScreenBinding
import com.mirza.adil.ui.base.BaseActivity
import com.mirza.adil.ui.base.BaseFragment


class TransactionScreenFragment : BaseFragment<FragmentTransactionScreenBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentTransactionScreenBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setup()
    }

    private fun setup() = with(binding) {
        visibleToolbar()
    }

    private fun visibleToolbar() {
        if ((activity as BaseActivity).binding.toolbar.visibility == View.GONE) (activity as BaseActivity).binding.toolbar.visibility =
            View.VISIBLE
    }
}