package jokeapp.kishor_android.example.com.jokepresenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);
        String joke = getIntent().getStringExtra("JOKE");
        ((AppCompatTextView)findViewById(R.id.text_view_joke)).setText(joke);
    }
}
