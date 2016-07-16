package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by kishor on 14/7/16.
 */
public class JokeFetchAsyncTaskTest extends InstrumentationTestCase implements GetJokeTask.AsyncTaskResponseListener{

    CountDownLatch latch;
    GetJokeTask getJokeTask;
    Context context;
    private final String TEST_NAME = "TEST";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        latch = new CountDownLatch(1);
        context = getInstrumentation().getTargetContext();
        getJokeTask = new GetJokeTask(context);
        getJokeTask.setAsyncTaskResponseListener(this);
    }

    public void testVerifyAsyncTaskResponse() throws Throwable {
        runTestOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  getJokeTask.execute(TEST_NAME);
                                  try {
                                      latch.await(30, TimeUnit.SECONDS);
                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                              }
                          }
        );
    }

    @Override
    public void onAsyncResponse(String joke) {
        assertEquals("Hi, " + TEST_NAME, joke);
    }
}
