package com.manish.androiddevcourse

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        findViewById<Button>(R.id.btn_start_work).setOnClickListener {
            startOneTimeWork()
        }
    }

    private fun startOneTimeWork() {
        //getting the workmanager instance
        val workManager = WorkManager.getInstance(applicationContext)
        //making the work request (One time work request)
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java).build()
        //enqueuing the work request
        workManager.enqueue(uploadRequest)

        //adding a listener (live data observer) to get the status of the work enqueued
        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
            findViewById<TextView>(R.id.textView).text = it.state.name
        }
    }
}