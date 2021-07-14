package com.mirza.adil.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * The class BaseFragment
 *
 * @author  Mirza Adil
 * @email mirza.madil@gmail.com
 * @version 1.0
 * @since 14 Jul 2021
 *
 * <p>This class is used to handle all the generic operations and logic related to the fragment, In this class we implements the VB generic object for the view-binding
 * So we can use this implementation in child classes. It also has the method for toast() and logic for the destruction of binding object</p>
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    fun toast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun applicationContext(): Context = requireActivity().applicationContext

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
