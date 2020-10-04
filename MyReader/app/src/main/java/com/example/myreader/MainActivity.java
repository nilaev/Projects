package com.example.myreader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private List<String> quotes;
    private int numberOfList;
    int editNumberOfList = 1;
    SharedPreferences sPref;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String book = readBook();

        quotes = new BookParser().bookParse(book);
        quotes.add("");
        for (int i = quotes.size() - 2; i >= 0; i--) {
            quotes.set(i + 1, i + 1 + ". " + quotes.get(i));
            quotes.set(i + 1, quotes.get(i + 1).replace("\n", " "));
        }

        quoteTextView = findViewById(R.id.quoteText);
        loadNumberOfList();
        numberOfList = editNumberOfList;
        quoteTextView.setText(quotes.get(numberOfList));
        quoteTextView.setMovementMethod(new ScrollingMovementMethod());
        quoteTextView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeRight() {
                onPrevClick(quoteTextView);
            }

            @Override
            public void onSwipeLeft() {
                onNextClick(quoteTextView);
            }
        });
    }

    private String readBook() {
        InputStream is = getResources().openRawResource(R.raw.zametki);
        String s = "";
        try {
            s = IOUtils.toString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(is);
        return s;
    }

    public void onNextClick(View view) {
        if (numberOfList < quotes.size() - 1) {
            numberOfList++;
            quoteTextView.setText(quotes.get(numberOfList));
        }
    }

    public void onPrevClick(View view) {
        if (numberOfList > 1) {
            numberOfList--;
            quoteTextView.setText(quotes.get(numberOfList));
        }
    }

    // --- Блок для выбора страницы по номеру --- //
    public void onGotoClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Переход на страницу");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String inputValue = input.getText().toString();
                if (!inputValue.isEmpty()) {
                    int gotoList = Integer.parseInt(inputValue);
                    if (0 < gotoList && gotoList < quotes.size()) {
                        numberOfList = gotoList;
                    }
                }
                quoteTextView.setText(quotes.get(numberOfList));
            }
        });
        builder.show();
    }
    // --- конец блока --- //

    // --- Блок для сохранения последней страницы --- //
    void saveNumberOfList() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("saved_last_list", numberOfList);
        ed.apply();
    }

    void loadNumberOfList() {
        sPref = getPreferences(MODE_PRIVATE);
        editNumberOfList = sPref.getInt("saved_last_list", 1);
    }

    protected void onPause() {
        super.onPause();
        saveNumberOfList();
    }

    protected void onStop() {
        super.onStop();
        saveNumberOfList();
    }

    protected void onDestroy() {
        super.onDestroy();
        saveNumberOfList();
    }
    // --- конец блока --- //
}