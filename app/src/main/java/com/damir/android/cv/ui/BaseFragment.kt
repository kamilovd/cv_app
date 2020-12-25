package com.damir.android.cv.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment(@LayoutRes layoutRes: Int = 0): Fragment(layoutRes) {
    protected val job = Job()
    protected val coroutineScope = CoroutineScope(job + Dispatchers.Main)
}