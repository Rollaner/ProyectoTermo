package com.example.caloriecountxml

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File

import java.io.IOException




class HistoryWorker (context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    @Throws(IOException::class)
    override fun doWork(): Result {
        val appContext = applicationContext
        val filename = "Saved.csv"
        val calories = inputData.getString("CALORIES")?:return Result.failure()
        val tdee = inputData.getString("TDEE")?:return Result.failure()
        val date = inputData.getString("DATE")?:return Result.failure()
        val fileContents = ArrayList<String>(1)
        fileContents.add("$date,$tdee,$calories,\n")
        val file = File(appContext.filesDir, filename)
        //Guardar datos en shared preferences o bien shared storage
        if(fileContents.size == 1){ //si es nuevo
            file.writeText(fileContents[0])
            return Result.success()
        }
        if(fileContents.size <= 30) { //si es menor o igual a 30
            for(i in 1..30) {
                file.appendText(fileContents[i])
            }
        }else{
            fileContents.removeAt(0) //lo demas
            val aux = fileContents[0]
            file.writeText(aux)
            for(i in 1..30) {
                file.appendText(fileContents[i])
            }
        }
        return Result.success()
    }
}
