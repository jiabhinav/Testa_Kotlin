package com.radiant.rpl.testa_kotlin.ui.home

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.radiant.rpl.testa_kotlin.R
import com.radiant.rpl.testa_kotlin.databinding.FragmentContactUsBinding


class ContactUs : Fragment() {

    lateinit var binding:FragmentContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentContactUsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init()
    {
        binding.call1.setText("8929486900")
        binding.call2.setText("8929486904")
        binding.call3.setText("7303099344")
        binding.call4.setText("7303507547")
        binding.call6.setText("1800-22-1208")
        binding.Helpline2.setText(resources.getString(R.string.assistcall))
          binding.no6.setText(resources.getString(R.string.helpline_no))

        binding.Helpline22.setPaintFlags(binding.Helpline22.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.call1.setPaintFlags(binding.call1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.call2.setPaintFlags(binding.call2.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.call3.setPaintFlags(binding.call3.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.call4.setPaintFlags(binding.call4.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.call6.setPaintFlags(binding.call6.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }
}