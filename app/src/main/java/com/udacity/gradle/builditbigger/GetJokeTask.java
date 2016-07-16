package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.Joke;

import java.io.IOException;

import jokeapp.kishor_android.example.com.jokepresenter.JokeActivity;

/**
 * Created by kishor on 12/7/16.
 */
public class GetJokeTask extends AsyncTask<String,Void,String> {

    private Context mContext;

    public GetJokeTask(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        Joke joke = new Joke();
        try {
            return joke.getJoke(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        if(listener!=null){
            listener.onAsyncResponse(joke);
        }
        if(!joke.isEmpty()) {
            Intent jokeIntent = new Intent(mContext, JokeActivity.class);
            jokeIntent.putExtra("JOKE", joke);
            jokeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(jokeIntent);
        } else {
            Toast.makeText(mContext, "Connection Error", Toast.LENGTH_LONG).show();
        }
    }

    AsyncTaskResponseListener listener;
    public interface AsyncTaskResponseListener {
        void onAsyncResponse(String joke);
    }

    public void setAsyncTaskResponseListener(AsyncTaskResponseListener listener){
        this.listener = listener;
    }
}
