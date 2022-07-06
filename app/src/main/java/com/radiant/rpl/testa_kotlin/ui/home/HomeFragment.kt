package com.radiant.rpl.testa_kotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.radiant.rpl.testa_kotlin.R
import com.radiant.rpl.testa_kotlin.auth.Login
import com.radiant.rpl.testa_kotlin.adapter.IconsAdapter
import com.radiant.rpl.testa_kotlin.databinding.FragmentHomeBinding
import com.radiant.rpl.testa_kotlin.model.ItemModal
import com.radiant.rpl.testa_kotlin.utils.Utility.callReplaceFragment


class HomeFragment : Fragment(),View.OnClickListener {
    lateinit var navHostFragment:NavHostFragment
    private val icons = intArrayOf(
        R.drawable.ic_grade,
        R.drawable.ic_grade,
        R.drawable.ic_grade,
        R.drawable.ic_grade,
        R.drawable.ic_grade)

      var iconsName:Array<String>?=null

    private val arrayList =ArrayList<ItemModal>()
    lateinit var iconsAdapter: IconsAdapter


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding==null)
        {
            _binding = FragmentHomeBinding.inflate(inflater, container, false)

            navHostFragment=requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
            iconsName=resources.getStringArray(R.array.reg_list)
            iconsAdapter = IconsAdapter(requireActivity(), arrayList)
            binding.recyclerView.setAdapter(iconsAdapter)
            addIcons()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init()
    {
        binding.loginScreen.setOnClickListener(this)
    }



    private fun addIcons() {
        binding.recyclerView.setHasFixedSize(true)
        arrayList.clear()
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        for (i in icons.indices) {
            val model = ItemModal()
            model.image=icons[i]
            model.name= iconsName?.get(i)
            arrayList.add(model)
        }
        iconsAdapter.notifyDataSetChanged()

        iconsAdapter.setOnClickListener(object : IconsAdapter.OnClickItem {
            override fun onClick(position: Int) {
                if (position==0)
                {
                    callReplaceFragment(navHostFragment,R.id.nav_compdropdown,null)
                }
                else if (position==1)
                {
                    callReplaceFragment(navHostFragment,R.id.nav_compdropdown,null)
                }
                else if (position==2)
                {

                }
                else if (position==5)
                {

                }
                else if (position==4)
                {

                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
      if (v?.id==R.id.login_Screen)
      {
          resultLauncher.launch(Intent(requireActivity(), Login::class.java))
      }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == 101) {
            callReplaceFragment(navHostFragment,R.id.nav_welcomepage,null)
        }
    }

}