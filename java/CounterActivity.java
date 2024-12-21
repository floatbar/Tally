package org.store.chromium;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CounterActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String counter;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.textView);

        ImageButton decrementButton = findViewById(R.id.decrementButton);
        ImageButton resetButton = findViewById(R.id.resetButton);
        ImageButton incrementButton = findViewById(R.id.incrementButton);

        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(Constants.COUNTER_PREF_NAME, Context.MODE_PRIVATE);
        counter = sharedPreferences.getString(Constants.COUNTER_PREF_KEY, "0");

        textView.setText(counter);

        decrementButton.setOnClickListener(v -> decreaseCounter());
        resetButton.setOnClickListener(v -> resetCounter());
        incrementButton.setOnClickListener(v -> increaseCounter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.counter_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finishAffinity();
        return super.onOptionsItemSelected(item);
    }

    private void decreaseCounter() {
        final int counterResult = Integer.parseInt(counter);
        if (counterResult > 0) {
            counter = String.valueOf(counterResult - 1);
            textView.setText(counter);
            sharedPreferences.edit().putString(Constants.COUNTER_PREF_KEY, counter).apply();
        }
    }

    private void resetCounter() {
        textView.setText("0");
        counter = "0";
        sharedPreferences.edit().putString(Constants.COUNTER_PREF_KEY, "0").apply();
    }

    private void increaseCounter() {
        counter = String.valueOf(Integer.parseInt(counter) + 1);
        textView.setText(counter);
        sharedPreferences.edit().putString(Constants.COUNTER_PREF_KEY, counter).apply();
    }
}
