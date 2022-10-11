package com.miladsh7.mytodolist.view.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.miladsh7.mytodolist.utils.LocaleManager

abstract class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(newBase!!))
    }
}
