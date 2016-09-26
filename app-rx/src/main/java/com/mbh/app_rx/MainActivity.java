package com.mbh.app_rx;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    Handler mHandler;
    Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();

        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(clickedView -> {

        });


        List<String> stringList = Arrays.asList("Hello", "World");
//        Observable<List<String>> listObservable = Observable.just(stringList);
        Observable.from(stringList)
                .filter(s -> s.contains("o"))
                .map(String::toUpperCase)
                .reduce(new StringBuilder(), StringBuilder::append)
                .subscribe(this::showToast, Throwable::printStackTrace, () -> Log.i("FINISHED", "FINISHED"));
    }

    void showToast(String text) {
        Log.i("TEST", text);
        mHandler.post(() ->
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show()
        );
    }

    void showToast(StringBuilder text) {
        Log.i("TEST", text.toString());
        mHandler.post(() ->
                Toast.makeText(MainActivity.this, text.toString(), Toast.LENGTH_SHORT).show()
        );
    }
}
