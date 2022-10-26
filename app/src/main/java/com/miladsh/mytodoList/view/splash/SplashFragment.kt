package com.miladsh.mytodoList.view.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.miladsh.mytodoList.R
import com.miladsh.mytodoList.databinding.FragmentSplashBinding
import com.miladsh.mytodoList.utils.EXPLAIN_KEY
import com.miladsh.mytodoList.utils.SHARE_PREF_NAME
import com.miladsh.mytodoList.view.base.BaseFragment
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment<FragmentSplashBinding>(
    R.layout.fragment_splash,
    FragmentSplashBinding::class
) {

    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.coroutineScope.launchWhenCreated {
            delay(1000)
            val explainIsVisited = getBoolean(EXPLAIN_KEY, defaultValue = false)
            if (explainIsVisited) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToExplainFragment())
            }
        }
    }

    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}