package com.manish.androiddevcourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(initialCount: Int) : ViewModel() {
    private val _count = MutableLiveData(initialCount)
    val count: LiveData<Int>
        get() = _count

    fun updateCount() {
        _count.value = count.value?.inc() ?: -1
    }
}