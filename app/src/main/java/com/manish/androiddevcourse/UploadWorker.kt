package com.manish.androiddevcourse

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        return try {
            for (i in 1..600000) {
                Log.i("MYTAG", "doWork: $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }

    }
}