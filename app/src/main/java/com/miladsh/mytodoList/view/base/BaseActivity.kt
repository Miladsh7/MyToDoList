package com.miladsh.mytodoList.view.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.miladsh.mytodoList.utils.LocaleManager

abstract class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase!!))
    }
}
