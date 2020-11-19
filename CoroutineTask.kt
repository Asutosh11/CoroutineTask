package com.asutosh.util.coroutine

import kotlinx.coroutines.*


abstract class CoroutineTask<Params, Progress, Result> {

    /* AsyncTask has been deprecated from Android 11
     *
     * CoroutineTask is the solution, if you want to seamlessly update your AsyncTasks to Coroutines.
     * You don't need to change anything in your AsyncTask methods, parameters or logic.
     * It Doesn't matter if your classes are in Java or Kotlin.
     */

    val uiScope = CoroutineScope(Dispatchers.Main)
    val backgroundScope = CoroutineScope(Dispatchers.IO)
    var result: Result? = null
    var preExecuteJob : Job? = null
    var postExecuteJob : Job? = null
    var doInBackgroundJob : Job? = null

    abstract fun onPreExecute()

    abstract fun doInBackground(vararg params: Params): Result

    abstract fun onPostExecute(result: Result?)

    open fun onCancelled(){}

    open fun onProgressUpdate(vararg progress: Progress) {}

    open fun execute(vararg params: Params) {

        preExecuteJob = uiScope.launch {

            onPreExecute()

            backgroundScope.async {

                if(backgroundScope.isActive){
                    result = doInBackground(*params)
                    postExecuteJob = uiScope.launch {
                        onPostExecute(result)
                    }
                }
            }.await()
        }
    }

    open fun cancel(hasToCancel: Boolean){
        if(preExecuteJob?.isActive?:false && hasToCancel){
            preExecuteJob!!.cancel()
        }
        if(backgroundScope.isActive && hasToCancel){
            doInBackgroundJob!!.cancel()
        }
        if(postExecuteJob?.isActive?:false && hasToCancel){
            postExecuteJob!!.cancel()
        }
        onCancelled()
    }
}

