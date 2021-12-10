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
        var fileArray = ArrayList<String>(1)
        val file = File(appContext.filesDir, filename)

        if(!file.exists()) file.createNewFile()
        val fileData = ArrayList(file.readLines())

        if(fileData.isNotEmpty()) fileArray = fileData
        fileArray.add("$date,$tdee,$calories,\n")
        //Guardar datos en shared preferences o bien shared storage
        if(fileArray.size <= 30) { //si es menor o igual a 30
            file.writeText(fileArray[0])
            for(i in 1..30) {
                file.appendText(fileArray[i])
            }
        }else{
            fileArray.removeAt(0) //lo demas
            val aux = fileArray[0]
            file.writeText(aux)
            for(i in 1..30) {
                file.appendText(fileArray[i])
            }
        }
        return Result.success()
    }

}
