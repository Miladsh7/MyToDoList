package com.miladsh7.mytodolist.view.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.databinding.FragmentSplashBinding
import com.miladsh7.mytodolist.utils.EXPLAIN_KEY
import com.miladsh7.mytodolist.utils.SHARE_PREF_NAME
import com.miladsh7.mytodolist.view.base.BaseFragment
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