# CoroutineTask 
<img src = "https://github.com/Asutosh11/CoroutineTask/blob/main/clock-image.png" height="100">

<em>AsyncTask has been deprecated in Android 11.</em> <br/>
There are a lot of alternatives to AsyncTask like RxJava, Services, Coroutines, etc.

<b>CoroutineTask is a utility that has the same structure as AsyncTask but uses Coroutines underneath</b><br/>


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

#### Example usage in Kotlin

```Kotlin
val asyncTask = object: CoroutineTask <Void, Void, String> () {

    override fun onPreExecute() {}

    override fun doInBackground(vararg params: Void): String {
        return ""
    }

    override fun onPostExecute(result: String ? ) {}

    override fun onCancelled() {
        // optional
    }
}

asyncTask.execute()
// if you have to cancel it
asyncTask.cancel(true)
```

#### Example usage in Java

```Java
CoroutineTask asyncTask = new CoroutineTask <Void, Void, String> () {

    @Override
    public void onPreExecute() {}

    @Override
    public String doInBackground(Void...params) 
        return "";
    }

    @Override
    public void onPostExecute(String result) {}

    @Override
    public void onCancelled() {
        // optional
    }
};

coroutineTask.execute();
// if you have to cancel it
coroutineTask.cancel(true);
```

#### How to cancel the task?
```
asyncTask.cancel(true);
```
