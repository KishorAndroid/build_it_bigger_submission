package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.Joke;

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
        return joke.getJoke(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent jokeIntent = new Intent(mContext, JokeActivity.class);
        jokeIntent.putExtra("JOKE", s);
        mContext.startActivity(jokeIntent);
    }
}
