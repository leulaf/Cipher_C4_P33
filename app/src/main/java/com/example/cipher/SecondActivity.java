package com.example.cipher;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
    public EditText offSet;
    public TextView output;
    public String encrypted;

    // parses the number in the text if it's possible
    public static int tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String cipher(String str, int offset){
        StringBuilder result = new StringBuilder();
        for (char character : str.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        output = (TextView) findViewById(R.id.change);
        offSet = (EditText) findViewById(R.id.offset);
        encrypted = getIntent().getStringExtra("input");
        int currOffSet = getIntent().getIntExtra("offSet", 0);
        output.setText("Encrypted Text:\n" + encrypted);
        encrypted = cipher(encrypted, currOffSet * -1); // deciphers the text from the first activity


        offSet.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                 int change = tryParse(offSet.getText().toString());
                 output.setText("Encrypted Text:\n" + cipher(encrypted, change));
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

    }
}
