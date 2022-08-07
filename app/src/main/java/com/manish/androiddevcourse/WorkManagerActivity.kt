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
        val workManager = WorkManager.getInstance(applicationContext)
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java).build()
        workManager.enqueue(uploadRequest)
        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
            findViewById<TextView>(R.id.textView).text = it.state.name
        }
    }
}