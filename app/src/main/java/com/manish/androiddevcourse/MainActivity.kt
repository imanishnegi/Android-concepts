package com.manish.androiddevcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.manish.androiddevcourse.data.Student
import com.manish.androiddevcourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityDataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityDataBinding.student = getStudent()
    }

    private fun getStudent(): Student {
        return Student(1,"Manish","manishng3@gmail.com")
    }
}