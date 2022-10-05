package com.example.cipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public EditText inputText;
    public Button switchToSecondActivity;
    public static int fixedOffSet = 1;
    String input = "";

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
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.input);
//        input = inputText.getText().toString();
        switchToSecondActivity = findViewById(R.id.encrypt);
        switchToSecondActivity.setOnClickListener(view -> switchActivities());
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, SecondActivity.class);
        input = cipher(inputText.getText().toString(), fixedOffSet);
        switchActivityIntent.putExtra("input", input);
        switchActivityIntent.putExtra("offSet", fixedOffSet);
        startActivity(switchActivityIntent);
    }
}