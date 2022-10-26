package com.miladsh.mytodoList.view.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.miladsh.mytodoList.utils.viewBinding
import kotlin.reflect.KClass

abstract class BaseFragment<out VB : ViewBinding>(
    @LayoutRes contentLayoutId: Int,
    viewBindingKlass: KClass<VB>
) : Fragment(contentLayoutId) {

    protected val binding: VB by viewBinding(clazz = viewBindingKlass)
}


