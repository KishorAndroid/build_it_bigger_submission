package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import jokeapp.kishor_android.example.com.jokepresenter.JokeActivity;


public class MainActivity extends ActionBarActivity implements GetJokeTask.AsyncTaskResponseListener{

    GetJokeTask getJokeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        getJokeTask = new GetJokeTask(this);
        getJokeTask.setAsyncTaskResponseListener(this);
        getJokeTask.execute();
    }

    @Override
    public void onAsyncResponse(String joke) {
        if(!joke.isEmpty()) {
            Intent jokeIntent = new Intent(this, JokeActivity.class);
            jokeIntent.putExtra("JOKE", joke);
            jokeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(jokeIntent);
        } else {
            Toast.makeText(this, "Connection Error", Toast.LENGTH_LONG).show();
        }
    }
}
