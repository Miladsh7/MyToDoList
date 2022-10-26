package com.miladsh.mytodoList.view.explain

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.miladsh.mytodoList.R
import com.miladsh.mytodoList.databinding.FragmentExplainBinding
import com.miladsh.mytodoList.utils.EXPLAIN_KEY
import com.miladsh.mytodoList.utils.SHARE_PREF_NAME
import com.miladsh.mytodoList.view.base.BaseFragment

class ExplainFragment : BaseFragment<FragmentExplainBinding>(
    R.layout.fragment_explain,
    FragmentExplainBinding::class
) {

    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(
            SHARE_PREF_NAME, Context.MODE_PRIVATE
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.extButtonExplain.setOnClickListener {
            val action = ExplainFragmentDirections.actionExplainFragmentToHomeFragment()
            findNavController().navigate(action)
            save(EXPLAIN_KEY, true)
        }
    }

    private fun save(key: String, value: Boolean?) {
        value?.let { sharedPreferences.edit().putBoolean(key, it).apply() }
    }
}