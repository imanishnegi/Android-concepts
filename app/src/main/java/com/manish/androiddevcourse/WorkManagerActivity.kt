package com.manish.androiddevcourse

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class WorkManagerActivity : AppCompatActivity() {
    companion object {
        const val KEY_INPUT_DATA = "input_data"
    }

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
        //setting constraints to be met for the work request to run (now the work will run only when phone is charging)
        val constraints = Constraints.Builder()
            .setRequiresCharging(true).build()

        //sending input data to the workmanager
        val inputData = Data.Builder()
            .putInt(KEY_INPUT_DATA, 300000)
            .build()
        //making the work request (One time work request)
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val downloadingRequest = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        // to enqueue parallel work request we need to add the work requests to a mutable list
        val parallelWorkRequests = mutableListOf(downloadingRequest, filteringRequest)
        //enqueuing the work request
        workManager
            .beginWith(parallelWorkRequests)
            .then(compressingRequest)
            .then(uploadRequest)
            .enqueue()

        //adding a listener (live data observer) to get the status of the work enqueued
        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(this) {
            findViewById<TextView>(R.id.textView).text = it.state.name

            //getting the output data received from the worker class when task is finished
            if (it.state.isFinished) {
                Toast.makeText(
                    applicationContext,
                    it.outputData.getString(UploadWorker.KEY_OUTPUT_DATA),
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }
}