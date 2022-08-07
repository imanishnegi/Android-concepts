package com.manish.androiddevcourse

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * This is the worker class containing the work description
 */
class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    companion object {
        const val KEY_OUTPUT_DATA = "output_data"
    }

    override fun doWork(): Result {
        val inputData = inputData.getInt(WorkManagerActivity.KEY_INPUT_DATA, 0)
        return try {
            for (i in 1 until inputData) {
                Log.i("MYTAG", "doWork: $i")
            }

            //creating output data to send with the work manager result
            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val date = time.format(Date())
            val outputData = Data.Builder()
                .putString(KEY_OUTPUT_DATA, date)
                .build()
            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }

    }
}