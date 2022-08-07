package com.manish.androiddevcourse

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * This is the worker class containing the work description
 */
class DownloadingWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            for (i in 1 until 30000) {
                Log.i("MYTAG", "Downloading: $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }

    }
}