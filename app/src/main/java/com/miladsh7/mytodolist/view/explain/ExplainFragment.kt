package com.miladsh7.mytodolist.view.explain

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.databinding.FragmentExplainBinding
import com.miladsh7.mytodolist.utils.EXPLAIN_KEY
import com.miladsh7.mytodolist.utils.SHARE_PREF_NAME
import com.miladsh7.mytodolist.view.base.BaseFragment

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