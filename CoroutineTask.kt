package com.asutosh.util.coroutine

import kotlinx.coroutines.*

abstract class CoroutineTask<Params, Progress, Result> {

    /* AsyncTask has been deprecated from Android 11
     *
     * CoroutineTask is a Utility class, if you want to seamlessly update your AsyncTasks to Coroutines.
     * You don't need to change anything in your AsyncTask methods, parameters or logic.
     * It Doesn't matter if your classes are in Java or Kotlin.
     */

    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val backgroundScope = CoroutineScope(Dispatchers.Default)
    private var result: Result? = null

    protected abstract fun onPreExecute()

    protected abstract fun doInBackground(vararg params: Params?): Result

    protected abstract fun onPostExecute(result: Result?)

    protected fun onCancelled(){}

    protected open fun onProgressUpdate(progress: Progress?) {}

    fun execute(vararg params: Params) {

        uiScope.launch {

            uiScope.launch {
                onPreExecute()

                var result = backgroundScope.async {
                    doInBackground(*params)
                }

                uiScope.launch {
                    onPostExecute(result.await())
                }
            }
        }
    }

    fun cancel(hasToCancel: Boolean){
        if(hasToCancel){
            uiScope.cancel("coroutine cancelled by user")
            backgroundScope.cancel("coroutine cancelled by user")
            uiScope.launch {
                onPostExecute(null)
                onCancelled()
            }
        }
    }

    protected fun publishProgress(progress: Progress) {
        uiScope.launch{
            onProgressUpdate(progress)
        }
    }
}
