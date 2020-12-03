# CoroutineTask 
<img src = "https://github.com/Asutosh11/CoroutineTask/blob/main/clock-image.png" height="100">

<em>AsyncTask has been deprecated in Android 11.</em> <br/>
There are a lot of alternatives to AsyncTask like RxJava, Services, Coroutines, etc.

<b>CoroutineTask is a utility that has the same structure as AsyncTask but uses Kotlin Coroutines underneath</b><br/>


## How to use? 
In the same way you use AsyncTask, just replace the keyword <b>AsyncTask</b> with <b>CoroutineTask</b>

#### Add build.gradle dependency
```groovy
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'
```

#### Save this file somewhere in your codebase
```text
CoroutineTask.kt
```

#### Example 1: usage in Kotlin

```Kotlin
val asyncTask = object: CoroutineTask <String?, Int?, String?>() {

    override fun onPreExecute() {}

    override fun doInBackground(vararg params: String?): String? {

        for (i in 0. .100000) {
            // optional, publishing the progress
            val progress: Int = (i / 1000)
            publishProgress(progress)
        }
        return params[0]
    }

    override fun onPostExecute(result: String?) {
        Toast.makeText(applicationContext, result, LENGTH_LONG).show()
    }

    override fun onCancelled() {}

    override fun onProgressUpdate(progress: Int?) {
        Log.i("progress: ", "" + progress)
    }
}

asyncTask.execute("background work completed")
```

#### Example 2: usage in Kotlin
```Kotlin
val asyncTask: CoroutineTask <Void?, Void?, String?> =

    object : CoroutineTask <Void?, Void?, String?>() {

        override fun onPreExecute() {
            // do something
        }

        override fun doInBackground(vararg params: Void?): String? {
            return ""
        }

        override fun onPostExecute(result: String?) {
            // do something
        }

        override fun onCancelled() {
            // optional
        }

    }

asyncTask.execute()
```

#### Example 3: usage in Java

```Java
CoroutineTask asyncTask = new CoroutineTask <Void, Void, String>() {

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(Void... params) {
        return "";
    }

    @Override
    protected void onPostExecute(String result) {}

    @Override
    protected void onCancelled() {
        // optional
    }
};

asyncTask.execute();
```

#### Example 4: usage in Java (Simplest usage)
```Java
CoroutineTask asyncTask = new CoroutineTask <Void, Void, String>() {

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(Void... params) {
        return "";
    }

    @Override
    protected void onPostExecute(String result) {}
};

asyncTask.execute();
```

#### How to cancel the task?
```
asyncTask.cancel(true);
```
